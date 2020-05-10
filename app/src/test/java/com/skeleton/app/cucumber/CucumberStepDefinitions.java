package com.skeleton.app.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.skeleton.app.repositories.AccountRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
public abstract class CucumberStepDefinitions extends MockMvcResultMatchers {

  private static ThreadLocal<Map<String, Object>> contexts = new ThreadLocal<>();

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  protected AccountRepository accountRepository;

  @Autowired
  protected WireMockServer mockServer;


  protected static void initContext() {
    contexts.set(new HashMap<>());
  }

  protected static <T> T getFromContext(String name) {
    return (T) contexts.get().get(name);
  }

  protected static <T> void keepInContext(String name, T value) {
    contexts.get().put(name, value);
  }

  protected final MvcResult post(String url, Object contentValue, Object... args) throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
            .post(url, args)
            .contentType(APPLICATION_JSON_VALUE)
            .contentType(APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(contentValue));
    builder.accept(APPLICATION_JSON_VALUE);
    return this.mockMvc.perform(builder).andReturn();
  }

  protected final MvcResult get(String url, MultiValueMap<String, String> parameters) throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
            .get(url)
            .contentType(APPLICATION_JSON_VALUE)
            .contentType(APPLICATION_JSON);
    builder.params(parameters);
    builder.accept(APPLICATION_JSON_VALUE);
    return this.mockMvc.perform(builder).andReturn();
  }

  protected final MvcResult delete(String url, MultiValueMap<String, String> parameters) throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
            .delete(url)
            .contentType(APPLICATION_JSON_VALUE)
            .contentType(APPLICATION_JSON);
    builder.params(parameters);
    builder.accept(APPLICATION_JSON_VALUE);
    return this.mockMvc.perform(builder).andReturn();
  }

}
