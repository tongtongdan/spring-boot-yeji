package likelion14th.blog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 자동 생성
public class Article {

    @Id // id 필드를 기본키(Primary Key)로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 값 자동 생성 - 1씩 증가
    @Column(updatable = false) // INSERT 가능, 이후 수정 불가
    private Long id;

    @Column(nullable = false) // not null 제약 "필수값"
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String author;

    public Article(String title, String content, String author,  String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
