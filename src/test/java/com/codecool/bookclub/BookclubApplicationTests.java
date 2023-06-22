package com.codecool.bookclub;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.security.authentication.LoginResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookclubApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void test_booksHomePage() throws Exception {
		AtomicReference<List<Book>> result = new AtomicReference<>();
		mockMvc.perform(get("/api/books/homepage"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andDo(handler -> {
					String json = handler.getResponse().getContentAsString();
					result.set(new ObjectMapper().readValue(json, new TypeReference<>() {}));
				});
		Assertions.assertEquals(4, result.get().size());
	}

	void test_generatePass() throws Exception {
		System.out.println("testpass: " + passwordEncoder.encode("testuser"));
	}

	@Test
	void test_loginExistingUserIncorrectPassword() throws Exception {
		mockMvc.perform(post("/api/authentication/login")
						.contentType("application/json; charset=utf-8")
						.content("{\"email\":\"test@test.pl\",\"password\":\"testuser1\"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void test_loginExistingUserCorrectPassword() throws Exception {
		mockMvc.perform(post("/api/authentication/login")
				.contentType("application/json; charset=utf-8")
				.content("{\"email\":\"test@test.pl\",\"password\":\"testuser\"}"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").exists());
	}

	@Test
	void test_profilePageUnauthorized() throws Exception {
		mockMvc.perform(get("/api/users/profile"))
				.andExpect(status().isForbidden());
	}

	@Test
	void test_profilePageAuthorized() throws Exception {
		String response = mockMvc.perform(post("/api/authentication/login")
						.contentType("application/json; charset=utf-8")
						.content("{\"email\":\"test@test.pl\",\"password\":\"testuser\"}"))
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse().getContentAsString();
		LoginResponse loginResponse = objectMapper.readValue(response, LoginResponse.class);
		mockMvc.perform(get("/api/users/profile").header(HttpHeaders.AUTHORIZATION, "Bearer " + loginResponse.getToken()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.id").value(Long.valueOf(4)))
				.andExpect(jsonPath("$.nickname").value("user-test"))
				.andExpect(jsonPath("$.books").isArray())
				.andExpect(jsonPath("$.events").isArray())
				.andExpect(jsonPath("$.topics").isArray());
	}

//	@Test
//	void test_searchBooks() throws Exception {
//		String response = mockMvc.perform(get("api/books/search"))
//	}


}
