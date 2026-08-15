package likelion14th.blog.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import likelion14th.blog.domain.Article;
import likelion14th.blog.dto.response.ArticleDetailResponse;
import likelion14th.blog.dto.response.ArticleSummaryResponse;
import likelion14th.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleDetailResponse addArticle(String title, String content, String author, String password) {
        Article article = new Article(title, content, author, password);
        articleRepository.save(article);
        return ArticleDetailResponse.from(article);
    }

    @Transactional
    public ArticleDetailResponse getOneArticle(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("게시글 찾을 수 없음"));
        return ArticleDetailResponse.from(article);
    }

    @Transactional(readOnly = true) // 읽기 전용
    public List<ArticleSummaryResponse> getArticles() {
        List<Article> articles = articleRepository.findAll(); // 전체 게시글 조회

        List<ArticleSummaryResponse> articleResponses = articles.stream() // List -> Stream
                .map(ArticleSummaryResponse::from) // DTO 변환: Article -> ArticleSummaryResponse
                .toList(); // Stream -> List

        return articleResponses;
    }

    // 게시글 수정
    @Transactional
    public ArticleDetailResponse updateArticle(Long id, String title, String content) {
        // 해당 ID 게시글 조회
        Article article = articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        article.update(title, content); // 엔티티 수정
        articleRepository.save(article); // 수정된 게시글 저장
        return ArticleDetailResponse.from(article); // 응답 DTO로 변환 후 반환
    }

    @Transactional
    public Void deleteArticle(Long id) {
        articleRepository.deleteById(id);
        return null;
    }
}