package com.hadasht.steady.core.steady.domain;

import com.hadasht.steady.core.utils.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "STEADY_TEMPLATE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
		name = "STEADY_TEMPLATE_SEQ_GEN",
		sequenceName = "STEADY_TEMPLATE_SEQ"
)
public class SteadyTemplate extends BaseEntity {

	@Id
	@GeneratedValue(generator = "STEADY_TEMPLATE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	@Column(name = "template_id")
	private Long id;

	private String steadyName;
	private LocalTime startTime;
	private LocalTime endTime;

	public SteadyTemplate(String steadyName, LocalTime startTime, LocalTime endTime) {
		if (startTime.isAfter(endTime)) {
			throw new SteadyTemplateTimeException("시작 시간이 종료시간보다 이후입니다.");
		}
		this.steadyName = steadyName;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	public void changeStartTime(LocalTime startTime) {
		if (startTime.isAfter(endTime)) {
			throw new SteadyTemplateTimeException("시작 시간이 종료시간보다 이후입니다.");
		}
		this.startTime = startTime;
	}

	public void changeEndTime(LocalTime endTime) {
		if (startTime.isAfter(endTime)) {
			throw new SteadyTemplateTimeException("종료 시간이 시작시간보다 이전입니다.");
		}
		this.endTime = endTime;
	}

}
