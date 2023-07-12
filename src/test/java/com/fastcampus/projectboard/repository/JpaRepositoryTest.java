package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
@Import(JpaConfig.class)
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void givenTestData_whenInserting_thenWorkFind(){
        //given
        long previousCount = articleRepository.count();



        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @Test
    @DisplayName("update 테스트")
    void givenTestData_whenUpdating_thenWorkFind(){
        //given
        articleRepository.save(Article.of("new article", "new content", "#spring"));
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);



        //when
        Article savedArticle = articleRepository.save(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @Test
    @DisplayName("delete 테스트")
    void givenTestData_Deleting_thenWorkFind(){
        //given
        Article save = articleRepository.save(Article.of("new article", "new content", "#spring"));
        articleCommentRepository.save(ArticleComment.of(save, "야"));
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();





        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }
}
