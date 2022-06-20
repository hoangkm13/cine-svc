package com.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String commentText;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(updatable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;

}
