package com.hadasht.steady.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

class SteadyApiControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void getTodaySteadies() throws Exception {
		//given


		//when


		//then
		mockMvc.perform(get("/api/list"))
		       .andDo(print())
		       .andExpect(status().isOk());
	}
}