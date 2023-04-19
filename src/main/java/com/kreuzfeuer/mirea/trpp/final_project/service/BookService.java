package com.kreuzfeuer.mirea.trpp.final_project.service;

import com.kreuzfeuer.mirea.trpp.final_project.entity.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getListBookByUserLogin(String userLogin);

    void deleteBookByIdAndUserLogin(Long id, String userLogin);

    Book save(Book book);

    Book findBookByIdAndUserLogin(@Param("id") Long id, @Param("login") String userLogin);
}
