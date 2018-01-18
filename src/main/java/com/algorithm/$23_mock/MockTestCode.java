//package com.algorithm.$23_mock;
//
//import org.junit.Before;
//import org.junit.TestNio;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.ServletOutputStream;
//import javax.sound.midi.Soundbank;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DemoApplicationTests {
//	@Autowired
//	private WebApplicationContext context;
//	private MockMvc mockMvc;
//	@Before
//	public void setupMockMvc() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
//
//	@TestNio
//	public void testHello() throws Exception {
//		MvcResult perform = mockMvc
//				.perform(
//						post("/hello")
//								.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk())
//				.andReturn();
//		System.out.println(perform.getResponse().getContentAsString());
//
//	}
//	@TestNio
//	public void testHelloByRequestJson() throws Exception {
//		MvcResult json = mockMvc.perform(
//				post("/json/test")
//						.contentType(MediaType.APPLICATION_JSON_UTF8)
//						.content("json")//really json needed
//						.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk())
//				.andReturn();
//		System.out.println(json.getResponse().getContentAsString());
//	}
//
//
//
//
//}
