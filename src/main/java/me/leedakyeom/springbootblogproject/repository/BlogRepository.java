package me.leedakyeom.springbootblogproject.repository;

import me.leedakyeom.springbootblogproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> { // DAO 역할
}
