package com.hadasht.steady.core.steady.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class SteadyTemplateTest {

	@Test
	@DisplayName("객체생성")
	void createSteadyTemplateInstance() throws Exception {
		SteadyTemplate steadyTemplate = getSteadyTemplate("테스트", 5, 0, 10, 0);

		assertThat(steadyTemplate.getSteadyName()).isEqualTo("테스트");
	}



	@Test
	@DisplayName("객체 생성 예외 처리")
	void createSteadyTemplateException() throws Exception {

		Throwable thrown = catchThrowable(() -> getSteadyTemplate("테스트", 10,  5));

		assertThat(thrown).isInstanceOf(SteadyTemplateTimeException.class)
		                  .hasMessage("시작 시간이 종료시간보다 이후입니다.");

	}

	@Test
	@DisplayName("시작 시간 변경")
	void changeStartTime() throws Exception {
		SteadyTemplate template = getSteadyTemplate("테스트", 10,  20);

		//when
		Throwable thrown = catchThrowable(() -> template.changeStartTime(LocalTime.of(22, 0)));

		//then
		assertThat(thrown).isInstanceOf(SteadyTemplateTimeException.class)
		                  .hasMessage("시작 시간이 종료시간보다 이후입니다.");

	}

	@Test
	@DisplayName("시작 시간, 분 변경")
	void changeStartTimeMinute() throws Exception {
		SteadyTemplate template = getSteadyTemplate("테스트", 10,10,10,20);

		//when
		Throwable thrown = catchThrowable(() -> template.changeStartTime(LocalTime.of(10, 30)));

		//then
		assertThat(thrown).isInstanceOf(SteadyTemplateTimeException.class)
		                  .hasMessage("시작 시간이 종료시간보다 이후입니다.");

	}

	@Test
	@DisplayName("종료 시간 변경")
	void changeEndTime() throws Exception {
		SteadyTemplate template = getSteadyTemplate("테스트", 3,  6);

		//when
		Throwable thrown = catchThrowable(() -> template.changeEndTime(LocalTime.of(1, 0)));

		//then
		assertThat(thrown).isInstanceOf(SteadyTemplateTimeException.class)
		                  .hasMessage("종료 시간이 시작시간보다 이전입니다.");

	}

	@Test
	@DisplayName("종료 시간, 분 변경")
	void changeEndTimeMinute() throws Exception {
		SteadyTemplate template = getSteadyTemplate("테스트", 20,10,20,20);

		//when
		Throwable thrown = catchThrowable(() -> template.changeEndTime(LocalTime.of(10, 0)));

		//then
		assertThat(thrown).isInstanceOf(SteadyTemplateTimeException.class)
		                  .hasMessage("종료 시간이 시작시간보다 이전입니다.");

	}



	private SteadyTemplate getSteadyTemplate(String steadyName, int startHour, int endHour) {
		return getSteadyTemplate(steadyName, startHour, 0, endHour, 0);
	}

	private SteadyTemplate getSteadyTemplate(String steadyName, int startHour, int startMinute,
	                                         int endHour, int endMinute) {
		return new SteadyTemplate(steadyName,
				LocalTime.of(startHour, startMinute),
				LocalTime.of(endHour, endMinute));
	}
}