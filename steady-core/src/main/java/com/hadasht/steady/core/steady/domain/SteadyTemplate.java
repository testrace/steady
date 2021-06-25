package com.hadasht.steady.core.steady.domain;

import com.hadasht.steady.core.utils.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteadyTemplate extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
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
