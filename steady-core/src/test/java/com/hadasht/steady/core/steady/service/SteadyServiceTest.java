package com.hadasht.steady.core.steady.service;

import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import com.hadasht.steady.core.steady.repository.SteadyRepository;
import com.hadasht.steady.core.steady.repository.SteadyTemplateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SteadyServiceTest {

	@Autowired
	private SteadyRepository steadyRepository;

	@Autowired
	private SteadyTemplateRepository steadyTemplateRepository;

	@Autowired
	private SteadyService steadyService;

	@Test
	void test() throws Exception {
		//given
		SteadyTemplate template = new SteadyTemplate("테스트1", LocalTime.of(10, 0), LocalTime.of(11, 0));
		SteadyTemplate template2 = new SteadyTemplate("테스트2", LocalTime.of(9, 0), LocalTime.of(10, 0));
		SteadyTemplate template3 = new SteadyTemplate("테스트3", LocalTime.of(13, 0), LocalTime.of(15, 0));
		SteadyTemplate template4 = new SteadyTemplate("테스트4", LocalTime.of(20, 0), LocalTime.of(21, 0));
		SteadyTemplate template5 = new SteadyTemplate("테스트5", LocalTime.of(7, 10), LocalTime.of(8, 0));

		//when
		steadyTemplateRepository.save(template);
		steadyTemplateRepository.save(template2);
		steadyTemplateRepository.save(template3);
		steadyTemplateRepository.save(template4);
		steadyTemplateRepository.save(template5);

		//then
		List<SteadyTemplate> templates = steadyTemplateRepository.findAll();
		assertThat(templates).isNotEmpty().allMatch(steadyTemplate -> steadyTemplate.getSteadyName().contains("테스트"));

	}

	@Test
	void createSteadyForToday() throws Exception {
		//given
		test();

		//when
		List<Steady> listForToday = steadyService.getListForToday();

		//then
		assertThat(listForToday).isNotEmpty();
		assertThat(listForToday.size()).isEqualTo(5);

	}

}