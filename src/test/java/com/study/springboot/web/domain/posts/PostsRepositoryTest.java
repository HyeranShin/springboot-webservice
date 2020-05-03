package com.study.springboot.web.domain.posts;

import com.study.springboot.domain.posts.Posts;
import com.study.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정이 없을 경우 H2 데이터베이스 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //JUnit에서 단위 테스트가 끝날때마다 수행되는 메소드
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() //id 값이 있다면 update, 없다면 insert 쿼리 실행
                .title(title)
                .content(content)
                .author("hyeran.dev@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블에 있는 모든 데이터 조회
    }
}