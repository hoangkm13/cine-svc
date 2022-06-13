package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String role;

    @Column(length = 50, nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<View> views = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Favorite> favorites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Dislike> dislikes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
