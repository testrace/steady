package com.hadasht.steady.core.steady.domain;

import com.hadasht.steady.core.utils.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Steady extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id")
	private SteadyTemplate steadyTemplate;

	private LocalDate steadyDay;
	private boolean finished;

	public void finish() {
		this.finished = true;
	}

	public void cancelFinish() {
		this.finished = false;
	}

	public Steady(SteadyTemplate steadyTemplate) {
		this.steadyTemplate = steadyTemplate;
		this.finished = false;
		this.steadyDay = LocalDate.now();
	}

}
