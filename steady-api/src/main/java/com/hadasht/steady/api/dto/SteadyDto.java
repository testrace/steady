package com.hadasht.steady.api.dto;

import com.hadasht.steady.core.steady.domain.Steady;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SteadyDto {

	private Long steadyId;
	private String steadyName;
	private boolean finished;

	public static SteadyDto from(Steady steady) {
		return SteadyDto.builder()
		                .steadyId(steady.getId())
						.steadyName(steady.getTemplate().getSteadyName())
						.finished(steady.isFinished())
						.build();
	}

}
