package com.study.springboot.config.auth.dto;

import com.study.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //User 클래스를 사용하지 않는 이유 -> User 클래스에 직렬화를 구현하면 차후 성능 이슈, 부수 효과가 발생할 수 있음
    //인증된 사용자 정보만 필요
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
