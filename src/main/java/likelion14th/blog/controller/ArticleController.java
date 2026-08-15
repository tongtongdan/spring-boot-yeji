package likelion14th.blog.controller;

import likelion14th.blog.dto.request.ArticleRequest;
import likelion14th.blog.dto.response.ApiResponse;
import likelion14th.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import likelion14th.blog.dto.response.ArticleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ArticleResponse>> addArticle(@RequestBody ArticleRequest request) {
        ArticleResponse articleResponse =
                articleService.addArticle(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(201, "게시물 생성에 성공하였습니다.", articleResponse));
    }

    @GetMapping("/{id}") // GET 요청 처리 URL
    public ResponseEntity<ApiResponse<ArticleResponse>> getOneArticle (@PathVariable Long id) { // URL의 id값을 받아 특정 게시글 조회
        ArticleResponse articleResponse = articleService.getOneArticle(id); // 조회 결과 DTO(ArticleResponse: 게시글 정보)로 변환

        return ResponseEntity.ok(ApiResponse.success(200, "게시글 개별 조회에 성공하였습니다.",
                articleResponse));
    }
}
