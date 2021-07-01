package com.hadasht.steady.core.steady.service;

import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import com.hadasht.steady.core.steady.repository.SteadyQueryRepository;
import com.hadasht.steady.core.steady.repository.SteadyRepository;
import com.hadasht.steady.core.steady.repository.SteadyTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SteadyService {

	private final SteadyRepository steadyRepository;
	private final SteadyQueryRepository steadyQueryRepository;
	private final SteadyTemplateRepository steadyTemplateRepository;

	@Transactional
	public List<Steady> getListForToday() {
		List<Steady> steadies = steadyQueryRepository.getTodaySteady();
		if (steadies.isEmpty()) {
			List<SteadyTemplate> templates = steadyTemplateRepository.findAll(sortBy("startTime"));
			templates.stream().map(Steady::new).forEach(steadies::add);
			steadyRepository.saveAll(steadies);
		}
		return steadies;
	}

	public void finished(Long id) {
		Optional<Steady> steady = steadyRepository.findById(id);
		steady.ifPresent(Steady::finish);
	}

	private Sort sortBy(String id) {
		return Sort.by(Sort.Direction.ASC, id);
	}

}
