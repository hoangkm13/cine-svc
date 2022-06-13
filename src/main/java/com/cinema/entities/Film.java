package com.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;
    
    @Column(nullable = false)
    @Type(type = "text")
    private String description;
    
    @Column(nullable = false)
    private Integer maturity;
    
    @Column(length = 100, nullable = false)
    private String slug;

    @Column(length = 100, nullable = false)
    private Integer ratingStar;

    @ManyToMany
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "film_id")
    private List<View> views = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Favorite> favorites = new ArrayList<>();

    @Column(insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @ManyToMany
    @JoinTable(name = "actor_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "film")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "film")
    private List<Dislike> dislikes = new ArrayList<>();

    @OneToMany(mappedBy = "film")
    private List<Comment> comments = new ArrayList<>();
}
