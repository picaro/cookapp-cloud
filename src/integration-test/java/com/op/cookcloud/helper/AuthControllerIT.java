package com.op.cookcloud.helper;


import com.op.cookcloud.model.form.SignInForm;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alex on 24.06.2014.
 */
@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
public class AuthControllerIT {

    private SignInForm signInForm = new SignInForm();

    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void init(){
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenLoginPassedViaRest() throws Exception {
        log.info("AuthControllerIT","testLoginRest");
        signInForm.setEmail("test@test.com");
        signInForm.setPassword("11111");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(signInForm);

        HttpEntity<String> requestEntity = new HttpEntity<String>(json,headers);

        ResponseEntity<SignInForm> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8080/CookCloud/rest/auth/login/",
                requestEntity, SignInForm.class);

        assertEquals(202,responseEntity.getStatusCode().value());
        log.info("AuthControllerIT done", responseEntity);
    }

    @Test
    public void whenPasswordIncorrectViaRest() throws Exception {
        log.info("AuthControllerIT","testLoginRest");
        signInForm.setEmail("test@test.com");
        signInForm.setPassword("incorect");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(signInForm);

        HttpEntity<String> requestEntity = new HttpEntity<String>(json,headers);

        ResponseEntity<SignInForm> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8080/CookCloud/rest/auth/login/",
                requestEntity, SignInForm.class);

        assertEquals(302,responseEntity.getStatusCode().value());
        log.info("AuthControllerIT done", responseEntity);
    }
}
