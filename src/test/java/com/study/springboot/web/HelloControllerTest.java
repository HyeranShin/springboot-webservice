package com.study.springboot.web;

import com.study.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //스프링 실행자 -> 스프링 부트 테스트와 JUnit 사이 연결자
@WebMvcTest(controllers = HelloController.class, //Web(Spring MVC) 집중 테스트 -> @Controller, @ControllerAdvice 등 사용 가능)
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) //스캔 대상에서 제거
        })
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean) 주입
    private MockMvc mvc; //웹 API 테스트. 스프링 MVC 테스트의 시작점

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 HTTP GET 요청
                //mvc.perform 결과 검증
                .andExpect(status().isOk()) //HTTP Header의 Status 검증
                .andExpect(content().string(hello)); //응답 본문의 내용 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //API 테스트할때 사용될 요청 파라미터
                        .param("amount", String.valueOf(amount))) //String 값만 허용되므로 숫자/날짜 등의 데이터는 문자열로 변경해야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //JSON 응답값을 필드별로 검증. $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
