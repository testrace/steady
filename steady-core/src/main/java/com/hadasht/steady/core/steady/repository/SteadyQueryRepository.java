package com.hadasht.steady.core.steady.repository;

import com.hadasht.steady.core.steady.domain.Steady;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.hadasht.steady.core.steady.domain.QSteady.steady;
import static com.hadasht.steady.core.steady.domain.QSteadyTemplate.steadyTemplate;

@Repository
@RequiredArgsConstructor
public class SteadyQueryRepository {

	private final JPAQueryFactory queryFactory;

	public List<Steady> getTodaySteady() {
		return findAllBySteadyDay(LocalDate.now());
	}

	public List<Steady> findAllBySteadyDay(LocalDate steadyDay) {
		return queryFactory
				.selectFrom(steady)
				.innerJoin(steady.template, steadyTemplate)
				.fetchJoin()
				.where(steady.steadyDay.eq(steadyDay))
				.orderBy(steady.id.asc())
				.fetch();
	}

}
