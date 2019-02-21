package com.example.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ProductControllerTest extends DemojenkinsApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testProduct() throws Exception {
		mockMvc.perform(get("/api/products/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("LG P880 4X HD"))
				.andExpect(jsonPath("$.description").value("My first awesome phone!"))
				.andExpect(jsonPath("$.price").value(336.0))
				.andExpect(jsonPath("$.category_id").value(3))
				.andExpect(jsonPath("$.created").value("2014-05-31T18:12:26.000+0000"))
				.andExpect(jsonPath("$.modified").value("2014-05-31T10:12:26.000+0000"));

	}
}
