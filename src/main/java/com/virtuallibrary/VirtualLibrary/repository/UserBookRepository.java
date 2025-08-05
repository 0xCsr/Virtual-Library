package com.virtuallibrary.VirtualLibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtuallibrary.VirtualLibrary.model.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    public List<UserBook> findByUserId(Long userId);
    public List<UserBook> findByBookId(Long bookId);
    Optional<UserBook> findByUserIdAndBookId(Long userId, Long bookId);
    boolean existsByUserIdAndBookId(Long userId, Long BookId);
    void deleteByUserIdAndBookId(Long userId, Long bookId);
}
