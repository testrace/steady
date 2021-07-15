package com.hadasht.steady.api.web;

import com.hadasht.steady.api.dto.SteadyDto;
import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SteadyApiControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SteadyApiService steadyApiService;

	@Autowired
	private WebApplicationContext ctx;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

	@Test
	@DisplayName("오늘 목록 가져오기")
	void getTodaySteadies() throws Exception {
		//given
		List<SteadyDto> dtos = generateDto();

		//when
		when(steadyApiService.getTodaySteadies()).thenReturn(dtos);

		//then
		mockMvc.perform(get("/api/list"))
		       .andExpect(status().isOk())
				.andExpect(jsonPath("code").exists())
				.andExpect(jsonPath("data").exists())
				.andExpect(jsonPath("data").isArray())
				.andExpect(jsonPath("data[0].steadyId").exists())
				.andExpect(jsonPath("data[0].steadyName").exists())
				.andExpect(jsonPath("data[0].finished").exists())
				.andExpect(jsonPath("data[0].steadyDay").exists());
	}

	@Test
	@DisplayName("할 일 완료")
	void finished() throws Exception {
		//given
		final Long id = 1L;
		SteadyDto dto = SteadyDto.builder()
				.steadyId(id)
				.steadyName("할일01")
				.steadyDay(LocalDate.of(2021, 7, 15))
				.finished(true)
				.build();


		//when
		when(steadyApiService.finished(id)).thenReturn(dto);

		//then
		mockMvc.perform(
					post("/api/finished/"+id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaTypes.HAL_JSON)
				)
		        .andExpect(status().isOk())
				.andExpect(jsonPath("code").exists())
				.andExpect(jsonPath("data").exists())
				.andExpect(jsonPath("data").isMap())
				.andExpect(jsonPath("data.steadyId").value(id))
				.andExpect(jsonPath("data.steadyName").value(dto.getSteadyName()))
				.andExpect(jsonPath("data.finished").value(dto.isFinished()))
				.andExpect(jsonPath("data.steadyDay").value(dto.getSteadyDay().toString()));
	}



	private List<SteadyDto> generateDto() {
		List<SteadyDto> steadyDtos = generateSteadies()
				.stream()
                .map(SteadyDto::from)
                .collect(Collectors.toList());
		AtomicLong atomicLong = new AtomicLong();
		steadyDtos.forEach(dto -> dto.setSteadyId(atomicLong.incrementAndGet()));
		return steadyDtos;
	}

	private List<Steady> generateSteadies() {
		return IntStream.range(0, 5)
		         .mapToObj(this::generateTemplate)
		         .map(Steady::new)
		         .collect(Collectors.toList());
	}

	private SteadyTemplate generateTemplate(int i) {
		return new SteadyTemplate("할일" + i,
				LocalTime.of(i + 10, 0),
				LocalTime.of(i + 11, 0));
	}


}