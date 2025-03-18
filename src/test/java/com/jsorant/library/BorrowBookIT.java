package com.jsorant.library;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = BorrowBook.class)
public class BorrowBookIT {

  @Autowired
  private MockMvc mvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0").withExposedPorts(27017);

  @DynamicPropertySource
  static void containersProperties(DynamicPropertyRegistry registry) {
    mongoDBContainer.start();
    registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
    registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
  }

  @Test
  public void givenProduct_whenSave_thenGetProduct() throws Exception {
    MvcResult mvcResult = mvc
      .perform(
        post("/products") //.contentType("application/json")
        //.content(objectMapper.writeValueAsString(new Product("Banana", "Fruit", 10)))
      )
      .andExpect(status().isOk())
      .andReturn();

    String productId = mvcResult.getResponse().getContentAsString();

    mvc.perform(get("/products/" + productId)).andExpect(status().isOk());
  }
}
