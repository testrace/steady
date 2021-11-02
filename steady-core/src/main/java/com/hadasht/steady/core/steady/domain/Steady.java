package com.hadasht.steady.core.steady.domain;

import com.hadasht.steady.core.utils.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STEADY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
		name = "STEADY_SEQ_GEN",
		sequenceName = "STEADY_SEQ"
)
public class Steady extends BaseEntity {

	public static final Steady EMPTY = new Steady();

	public Steady(SteadyTemplate steadyTemplate) {
		this.template = steadyTemplate;
		this.finished = false;
		this.steadyDay = LocalDate.now();
	}

	@Id
	@GeneratedValue(generator = "STEADY_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	@Column(name = "steady_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", updatable = false)
	private SteadyTemplate template;

	private LocalDate steadyDay;
	private boolean finished;

	public void finish() {
		this.finished = true;
	}

}
