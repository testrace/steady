package com.hadasht.steady.core.steady.service;

import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import com.hadasht.steady.core.steady.repository.SteadyRepository;
import com.hadasht.steady.core.steady.repository.SteadyTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SteadyService {

	private final SteadyRepository steadyRepository;
	private final SteadyTemplateRepository steadyTemplateRepository;

	@Transactional
	public List<Steady> getListForToday() {
		List<Steady> steadies = steadyRepository.findAllBySteadyDay(LocalDate.now(), sortBy("id"));
		createSteadiesForToday(steadies);
		return steadies;
	}

	private void createSteadiesForToday(List<Steady> steadies) {
		if (steadies.isEmpty()) {
			List<SteadyTemplate> templates = steadyTemplateRepository.findAll(sortBy("startTime"));
			templates.stream().map(Steady::new).forEach(steadies::add);
		}
	}


	private Sort sortBy(String id) {
		return Sort.by(Sort.Direction.ASC, id);
	}
}
