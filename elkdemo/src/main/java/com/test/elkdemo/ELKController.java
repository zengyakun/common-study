package com.test.elkdemo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @ClassName ELKController
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/10/17 11:26
 * @Version 1.0
 **/
@RestController
public class ELKController {

    private static final Logger LOG = Logger.getLogger(ELKController.class.getName());
    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/elkdemo")
    public String demo() {
        String response = "Hello user !" + new Date();
        LOG.log(Level.INFO, "/elkdemo - >: " + response);
        return response;
    }

    @RequestMapping(value = "/elk")
    public String helloWorld() {
        String response = restTemplate.exchange("http://localhost:8080/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        }).getBody();
        LOG.log(Level.INFO, "/elk - >: " + response);
        try {
            String exceptionrsp = restTemplate.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
            }).getBody();
            LOG.log(Level.INFO, "/elk trying to print exception - >: " + exceptionrsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String rsp = "";
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            LOG.error("Exception as string :: - > :" + sStackTrace);
            rsp = sStackTrace;
        }
        return rsp;
    }
}
