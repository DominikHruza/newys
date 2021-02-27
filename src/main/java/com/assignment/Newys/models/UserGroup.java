package com.assignment.Newys.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private User groupOwner;

    @ManyToMany
    @JoinTable(
            name = "article_group",
            joinColumns = @JoinColumn(
                    name = "user_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "article_id", referencedColumnName = "id"))
    private Set<NewsArticle> articles = new HashSet<>();

    public UserGroup(String name, User groupOwner) {
        this.name = name;
        this.groupOwner = groupOwner;
    }

    public boolean addArticle(NewsArticle article){
        return articles.add(article);
    }
}
