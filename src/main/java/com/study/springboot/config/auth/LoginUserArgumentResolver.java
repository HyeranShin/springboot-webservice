package com.study.springboot.config.auth;

import com.study.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단

        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; //@LoginUser 어노테이션이 붙어있는지 확인
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); //파라미터 클래스 타입이 SessionUser.class인지 확인

        return isLoginUserAnnotation && isUserClass;
    }

    @Override //파라미터에 전달할 객체 생성
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}

//HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야함 (WebConfig 클래스 참고)