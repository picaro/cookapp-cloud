package com.op.cookcloud.helper;


import com.op.cookcloud.model.form.SignInForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Alex on 24.06.2014.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
public class AuthControllerIT {

    private MockMvc mockMvc;

    @Test
    public void testLoginRest() throws Exception {
        log.info("AuthControllerIT","testLoginRest");

        SignInForm signInForm = new SignInForm();
        signInForm.setEmail("test@test.com");
        signInForm.setPassword("11111");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<String>(
                "{\n" +
                "    \"email\": \"test@test.com\",\n" +
                "  \t\"password\": \"11111\"\n" +
                "}"
 ,headers);

//        String tradeXml = new RestTemplate().
//                postForEntity("http://localhost:8080/CookCloud/rest/auth/login/");
        ResponseEntity<SignInForm> entity = new RestTemplate().postForEntity(
                "http://localhost:8080/CookCloud/rest/auth/login/",
                requestEntity, SignInForm.class);

        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://localhost:8080/CookCloud/rest/auth/login/");
//        httpPost.setHeader("Accept","application/json");
//        httpPost.setHeader("Content-Type","application/json");
//        HttpEntity httpEntity = new StringEntity("{\n" +
//                "    \"email\": \"test@test.com\",\n" +
//                "  \t\"password\": \"11111\"\n" +
//                "}");
//        httpPost.setEntity(httpEntity);
//        CloseableHttpResponse response = null;
//        response = httpclient.execute(httpPost);
//
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        assertEquals(response.getEntity().getContentType().getValue(),"application/json; charset=UTF-8");
  //      log.info("AuthControllerIT", response.getEntity().getContent());
    }
}
