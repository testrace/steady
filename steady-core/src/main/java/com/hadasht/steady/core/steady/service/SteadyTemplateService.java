package com.hadasht.steady.core.steady.service;

import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import com.hadasht.steady.core.steady.repository.SteadyTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SteadyTemplateService {

    private final SteadyTemplateRepository steadyTemplateRepository;

    public SteadyTemplate save(SteadyTemplate steadyTemplate) {
        return steadyTemplateRepository.save(steadyTemplate);
    }
}
