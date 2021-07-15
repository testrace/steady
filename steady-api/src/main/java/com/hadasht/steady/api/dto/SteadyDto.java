package com.hadasht.steady.api.dto;

import com.hadasht.steady.core.steady.domain.Steady;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SteadyDto {

	private Long steadyId;
	private String steadyName;
	private boolean finished;
	private LocalDate steadyDay;

	public static SteadyDto from(Steady steady) {
		return SteadyDto.builder()
		                .steadyId(steady.getId())
						.steadyName(steady.getTemplate().getSteadyName())
						.steadyDay(steady.getSteadyDay())
						.finished(steady.isFinished())
						.build();
	}

}
