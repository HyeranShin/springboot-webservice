package com.study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //스프링 실행자 -> 스프링 부트 테스트와 JUnit 사이 연결자
@WebMvcTest //Web(Spring MVC) 집중 테스트 -> @Controller, @ControllerAdvice 등 사용 가능)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈(Bean) 주입
    private MockMvc mvc; //웹 API 테스트. 스프링 MVC 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 HTTP GET 요청
                //mvc.perform 결과 검증
                .andExpect(status().isOk()) //HTTP Header의 Status 검증
                .andExpect(content().string(hello)); //응답 본문의 내용 검증
    }
}
