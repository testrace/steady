package com.hadasht.steady.api.web;

import com.hadasht.steady.api.dto.SteadyDto;
import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.service.SteadyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SteadyApiService {

	private final SteadyService steadyService;

	public List<SteadyDto> getTodaySteadies() {
		List<Steady> listForToday = steadyService.getListForToday();
		return listForToday.stream()
		                   .map(SteadyDto::from)
		                   .collect(Collectors.toList());
	}

	public SteadyDto finished(Long id) {
		return SteadyDto.from(steadyService.finished(id));
	}

}
