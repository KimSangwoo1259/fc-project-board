package com.fastcampus.projectboard.repository.querydsl;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags();

}
