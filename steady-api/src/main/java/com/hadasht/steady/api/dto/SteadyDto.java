package com.hadasht.steady.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hadasht.steady.core.steady.domain.Steady;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class SteadyDto {

	private Long steadyId;
	private String steadyName;
	private boolean finished;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime endTime;
	private LocalDate steadyDay;

	public static SteadyDto from(Steady steady) {
		return SteadyDto.builder()
		                .steadyId(steady.getId())
						.steadyName(steady.getTemplate().getSteadyName())
		                .startTime(steady.getTemplate().getStartTime())
		                .endTime(steady.getTemplate().getEndTime())
						.steadyDay(steady.getSteadyDay())
						.finished(steady.isFinished())
						.build();
	}

}
