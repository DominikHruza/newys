package com.assignment.Newys.services.facades;

public interface ArticleLikesFacade {
    void addNewLike(Long articleId, String username);
    void removeLike(Long articleId, String username);
}
