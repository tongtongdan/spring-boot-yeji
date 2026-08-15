package likelion14th.blog.controller;

import likelion14th.blog.dto.request.ArticleRequest;
import likelion14th.blog.dto.request.UpdateArticleRequest;
import likelion14th.blog.dto.response.ApiResponse;
import likelion14th.blog.dto.response.ArticleSummaryResponse;
import likelion14th.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import likelion14th.blog.dto.response.ArticleDetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> addArticle(@RequestBody ArticleRequest request) {
        ArticleDetailResponse articleResponse =
                articleService.addArticle(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(201, "게시물 생성에 성공하였습니다.", articleResponse));
    }

    @GetMapping("/{id}") // GET 요청 처리 URL
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> getOneArticle (@PathVariable Long id) { // URL의 id값을 받아 특정 게시글 조회
        ArticleDetailResponse articleResponse = articleService.getOneArticle(id); // 조회 결과 DTO(ArticleResponse: 게시글 정보)로 변환

        return ResponseEntity.ok(ApiResponse.success(200, "게시글 개별 조회에 성공하였습니다.",
                articleResponse));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ArticleSummaryResponse>>> getArticles() {
        List<ArticleSummaryResponse> articleDetailResponses = articleService.getArticles(); // Service 호출: 전체 게시글 목록 조회

        return ResponseEntity.ok(ApiResponse.success(200, "게시글 전체 조회에 성공하였습니다.",
                articleDetailResponses));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        ArticleDetailResponse articleDetailResponse = articleService.updateArticle(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(ApiResponse.success(200, "게시글을 업데이트 하였습니다.",
                articleDetailResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(ApiResponse.success(204, "게시글을 삭제하는데 성공하였습니다."));
    }
}
