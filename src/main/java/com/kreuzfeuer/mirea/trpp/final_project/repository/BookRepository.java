package com.kreuzfeuer.mirea.trpp.final_project.repository;

import com.kreuzfeuer.mirea.trpp.final_project.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> getAllBookByUserLogin(String userLogin);

    @Modifying
    @Query(value = "DELETE FROM book B " +
            "USING t_user U " +
            "WHERE b.id =:id AND " +
            "u.login=:login",
            nativeQuery = true)
    void deleteBookByIdAndUserLogin(@Param("id") Long id, @Param("login") String userLogin);

    Book findBookByIdAndUserLogin(@Param("id") Long id, @Param("login") String userLogin);
}

