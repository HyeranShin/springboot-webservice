package com.study.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getName()).isEqualTo(name); //동등 비교 메소드 (assertThat 값과 isEqualTo 값이 같을때만 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}