package com.hadasht.steady.api.web;

import com.hadasht.steady.api.dto.SteadyTemplateDto;
import com.hadasht.steady.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class SteadyTemplateApiController {

    private final SteadyTemplateApiService steadyTemplateApiService;

    @PostMapping("/api/template")
    public ApiResponse save(@RequestBody SteadyTemplateDto steadyTemplateDto) {
        return ApiResponse.success(steadyTemplateApiService.save(steadyTemplateDto));
    }
}
