package com.kreuzfeuer.mirea.trpp.final_project.entity;

import com.kreuzfeuer.mirea.trpp.final_project.entity.enums.BookRating;
import com.kreuzfeuer.mirea.trpp.final_project.entity.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false )
    private String bookName;

    private String author;

    @Column(name = "date", nullable = false)
    private LocalDate dateAdded;

    private String description;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.PLANNED;

    @Enumerated(EnumType.STRING)
    private BookRating rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void init(){
        dateAdded = LocalDate.now();
    }
}
