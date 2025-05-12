package me.leedakyeom.springbootblogproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.leedakyeom.springbootblogproject.domain.Article;
import me.leedakyeom.springbootblogproject.dto.AddArticleRequest;
import me.leedakyeom.springbootblogproject.dto.UpdateArticleRequest;
import me.leedakyeom.springbootblogproject.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity()); // dto 객체를 dao를 통해 article 데이터 베이스에 저장한다.
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
