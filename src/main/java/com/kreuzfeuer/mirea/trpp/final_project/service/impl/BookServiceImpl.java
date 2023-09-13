package com.kreuzfeuer.mirea.trpp.final_project.service.impl;

import com.kreuzfeuer.mirea.trpp.final_project.entity.Book;
import com.kreuzfeuer.mirea.trpp.final_project.repository.BookRepository;
import com.kreuzfeuer.mirea.trpp.final_project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getListBookByUserLogin(String userLogin) {
        return bookRepository.getAllBookByUserLogin(userLogin);
    }

    @Override
    @Transactional
    public void deleteBookByIdAndUserLogin(Long id, String userLogin) {
        bookRepository.deleteBookByIdAndUserLogin(id, userLogin);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);

    }

    @Override
    public Optional<Book> findBookByIdAndUserLogin(Long id, String userLogin) {
        return bookRepository.findBookByIdAndUserLogin(id, userLogin);
    }

}
