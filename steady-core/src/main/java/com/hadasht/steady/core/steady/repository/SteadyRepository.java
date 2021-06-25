package com.hadasht.steady.core.steady.repository;

import com.hadasht.steady.core.steady.domain.Steady;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SteadyRepository extends JpaRepository<Steady, Long> {

	List<Steady> findAllBySteadyDay(LocalDate steadyDay, Sort sort);


}
