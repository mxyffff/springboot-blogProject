package me.leedakyeom.springbootblogproject.controller;

import lombok.RequiredArgsConstructor;
import me.leedakyeom.springbootblogproject.domain.Article;
import me.leedakyeom.springbootblogproject.dto.ArticleListViewResponse;
import me.leedakyeom.springbootblogproject.dto.ArticleViewResponse;
import me.leedakyeom.springbootblogproject.service.BlogService;
import org.hibernate.boot.model.relational.internal.SqlStringGenerationContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 1. 블로그 글 리스트 저장

        return "articleList"; // 2. articleList.html라는 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // 1. id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (required = false => id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // 2. id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // 3. id가 없으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
