package com.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //모든 필드의 get 메소드 생성
@NoArgsConstructor //기본 생성자 자동 추가 (public Posts() {}와 같은 효과)
@Entity //테이블과 링크될 클래스. 기본값으로 언더스코어 네이밍으로 테이블 이름 매칭
public class Posts {

    @Id //해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙 (auto_increment)
    private Long id;

    //테이블의 칼럼
    //굳이 선언하지 않아도 필드 모두 칼럼이 됨. 기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
