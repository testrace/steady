package com.hadasht.steady.api.web;

import com.hadasht.steady.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class SteadyApiController {

	private final SteadyApiService steadyApiService;

	@GetMapping("/api/list")
	public ApiResponse list() {
		return ApiResponse.success(steadyApiService.getSteadyToday());
	}


	@PostMapping("/api/finished/{id}")
	public ApiResponse finished(@PathVariable("id") Long id) {
		try {
			return ApiResponse.success(steadyApiService.finished(id));
		} catch (NullPointerException e) {
			return ApiResponse.fail("데이터 조회 실패");
		}
	}


}
