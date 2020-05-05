package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//메인 클래스
//@EnableJpaAuditing //JPA Auditing 활성화 ---> @@WebMvcTest를 위해 JpaConfig 클래스로 분리 -> 일반적인 @Configuration은 스캔 X)
@SpringBootApplication //스프링 부트, 스프링 Bean 읽기와 생성 자동 설정. 프로젝트의 최상단에 위치해야 함
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
