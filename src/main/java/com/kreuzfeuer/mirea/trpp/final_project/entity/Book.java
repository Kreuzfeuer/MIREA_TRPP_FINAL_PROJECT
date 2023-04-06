package com.kreuzfeuer.mirea.trpp.final_project.entity;

import com.kreuzfeuer.mirea.trpp.final_project.entity.enums.BookStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
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
    private BookStatus status;


}
