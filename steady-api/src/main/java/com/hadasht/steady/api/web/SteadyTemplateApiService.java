package com.hadasht.steady.api.web;

import com.hadasht.steady.api.dto.SteadyTemplateDto;
import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import com.hadasht.steady.core.steady.service.SteadyTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SteadyTemplateApiService {

    private final SteadyTemplateService steadyTemplateService;

    public SteadyTemplateDto save(SteadyTemplateDto steadyTemplateDto) {
        SteadyTemplate save = steadyTemplateService.save(steadyTemplateDto.from());
        return SteadyTemplateDto.from(save);
    }
}
