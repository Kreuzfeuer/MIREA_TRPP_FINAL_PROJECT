package com.kreuzfeuer.mirea.trpp.final_project.entity;


import jakarta.persistence.*;

import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 16, message = "Between 3 and 25 symbols")
    private String userName;

    @Size(min=3,max = 16, message = "Between 3 and 37 symbols")
    private String login;

    @Size(min = 2, max = 36, message = "Between 2 and 36 symbols")
    private String password;

    @Size(min = 4)
    private String email;

    @Transient
    private String passwordConfirm;
    @OneToMany(orphanRemoval = true)
    private List<Book> books;
}
