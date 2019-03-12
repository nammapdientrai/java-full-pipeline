package com.example.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.entity.Product;
import com.example.service.ProductsService;

public class ProductControllerTest extends DemojenkinsApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ProductsService ps;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testProduct() throws Exception {
		mockMvc.perform(get("/api/products/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("LG P880 4X HD"))
				.andExpect(jsonPath("$.description").value("My first awesome phone!"))
				.andExpect(jsonPath("$.price").value(336.0)).andExpect(jsonPath("$.category_id").value(3))
				.andExpect(jsonPath("$.created").value("2014-05-31T18:12:26.000+0000"))
				.andExpect(jsonPath("$.modified").value("2014-05-31T10:12:26.000+0000"));
	}

	@Test
	public void testCalculatorTotal() throws Exception {
		Optional<Product> product1 = ps.findEmployeeByID(1);
		Optional<Product> product2 = ps.findEmployeeByID(2);
		Optional<Product> product3 = ps.findEmployeeByID(3);
		Optional<Product> product4 = ps.findEmployeeByID(6);
		Optional<Product> product5 = ps.findEmployeeByID(7);
		
		Product p1 = product1.get();
		Product p2 = product2.get();
		Product p3 = product3.get();
		Product p4 = product4.get();
		Product p5 = product5.get();

		double result1 = (double) p1.getId() * p1.getPrice();
		double result2 = (double) p2.getId() * p2.getPrice();
		double result3 = (double) p3.getId() * p3.getPrice();
		double result4 = (double) p4.getId() * p4.getPrice();
		double result5 = (double) p5.getId() * p5.getPrice();

		double tmp1 = 336;
		double tmp2 = 598;
		double tmp3 = 1800;
		double tmp4 = 174;
		double tmp5 = 2793;

		Assert.assertEquals(result1, tmp1, 0.0);
		Assert.assertEquals(result2, tmp2, 0.0);
		Assert.assertEquals(result3, tmp3, 0.0);
		Assert.assertEquals(result4, tmp4, 0.0);
		Assert.assertEquals(result5, tmp5, 0.0);
	}
}
