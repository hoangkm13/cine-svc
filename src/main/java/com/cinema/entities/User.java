package com.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @Column(length = 50)
    private String username;

    @Column(length = 50)
    private String fullName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column()
    private String avatar;

    @NotBlank(message = "Giới tính không được để trống !")
    private String gender;

    @Column(length = 50)
    private String birthOfDate;

    @Column(length = 50)
    private String mobile;

    @Column()
    private String password;

    @Column(length = 20)
    private String role;

    @Column(length = 50)
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
