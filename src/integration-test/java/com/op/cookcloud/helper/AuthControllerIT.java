package com.op.cookcloud.helper;


import com.op.cookcloud.model.form.SignMeInForm;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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

    private SignMeInForm signInForm = new SignMeInForm();

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

        ResponseEntity<SignMeInForm> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8080/CookCloud/rest/auth/login/",
                requestEntity, SignMeInForm.class);

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

        ResponseEntity<SignMeInForm> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8080/CookCloud/rest/auth/login/",
                requestEntity, SignMeInForm.class);

        assertEquals(302,responseEntity.getStatusCode().value());
        log.info("AuthControllerIT done", responseEntity);
    }
}
