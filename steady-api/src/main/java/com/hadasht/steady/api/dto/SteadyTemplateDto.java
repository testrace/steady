package com.hadasht.steady.api.dto;

import com.hadasht.steady.core.steady.domain.SteadyTemplate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class SteadyTemplateDto {

    private Long templateId;
    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;

    public static SteadyTemplateDto from(SteadyTemplate steadyTemplate) {
        return SteadyTemplateDto.builder()
                .templateId(steadyTemplate.getId())
                .templateName(steadyTemplate.getSteadyName())
                .startTime(steadyTemplate.getStartTime())
                .endTime(steadyTemplate.getEndTime())
                .build();
    }

    public SteadyTemplate from() {
        return new SteadyTemplate(this.templateName, this.startTime, this.endTime);
    }
}
