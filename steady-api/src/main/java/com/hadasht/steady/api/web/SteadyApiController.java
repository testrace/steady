package com.hadasht.steady.api.web;

import com.hadasht.steady.api.dto.SteadyDto;
import com.hadasht.steady.core.steady.domain.Steady;
import com.hadasht.steady.core.steady.service.SteadyService;
import com.hadasht.steady.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SteadyApiController {

	private final SteadyService steadyService;

	@GetMapping("/api/list")
	public ApiResponse list() {
		List<Steady> listForToday = steadyService.getListForToday();
		List<SteadyDto> steadyDtos = listForToday.stream().map(SteadyDto::from).collect(Collectors.toList());
		return ApiResponse.success(steadyDtos);
	}

	@PostMapping("/api/finished/{id}")
	public ApiResponse finished(@PathVariable Long id) {
		Long finishedId = steadyService.finished(id);

		return ApiResponse.success(finishedId);
	}


}
