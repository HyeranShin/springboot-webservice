package com.study.springboot.config.auth;

import com.study.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면 사용을 위해 해당 옵션들 disable
                .and()
                    .authorizeRequests() //URL별 권한 관리 설정 옵션 시작점
                    //권한 관리 대상 지정
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //USER 권한을 가진 사람만
                    .anyRequest().authenticated() //설정된 값 이외 나머지 URL들 -> 로그인한 사용자만 허용
                .and()
                    .logout() //로그아웃 기능 설정 진입점
                        .logoutSuccessUrl("/") //로그아웃 성공 시 이동
                .and()
                    .oauth2Login() //OAuth 2 로그인 기능 설정 진입점
                        .userInfoEndpoint() //OAuth 2 로그인 성공 후 사용자 정보를 가져올때 설정
                            //소셜 로그인 성공시 후속 조치 진행할 UserService 인터페이스 구현체 등록
                            //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                            .userService(customOAuth2UserService);
    }
}
