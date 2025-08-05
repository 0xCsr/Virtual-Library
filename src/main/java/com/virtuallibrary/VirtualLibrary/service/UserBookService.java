package com.virtuallibrary.VirtualLibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virtuallibrary.VirtualLibrary.dto.UserBookDTO;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.BookIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.User.UserIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.UserBook.AlreadyAddedException;
import com.virtuallibrary.VirtualLibrary.exceptions.UserBook.UserBookNotFoundException;
import com.virtuallibrary.VirtualLibrary.model.Book;
import com.virtuallibrary.VirtualLibrary.model.User;
import com.virtuallibrary.VirtualLibrary.model.UserBook;
import com.virtuallibrary.VirtualLibrary.repository.BookRepository;
import com.virtuallibrary.VirtualLibrary.repository.UserBookRepository;
import com.virtuallibrary.VirtualLibrary.repository.UserRepository;

@Service
public class UserBookService {
    
    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserBookService(
        UserBookRepository userBookRepository,
        UserRepository userRepository,
        BookRepository bookRepository
    ) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<UserBook> findAll() {
        return userBookRepository.findAll();
    }

    public List<UserBook> findByUserId(Long id) {
        return userBookRepository.findByUserId(id);
    }

    public UserBook addBookToUser(UserBookDTO dto) {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new UserIdNotFoundException(dto.getUserId()));
    
        Book book = bookRepository.findById(dto.getBookId())
            .orElseThrow(() -> new BookIdNotFoundException(dto.getBookId()));

        if (userBookRepository.existsByUserIdAndBookId(user.getId(), book.getId()))
            throw new AlreadyAddedException();

        UserBook userBook = UserBook.builder()
            .user(user)
            .book(book)
            .status(dto.getStatus().trim())
            .build();

        return userBookRepository.save(userBook);
    }

    public void deleteByUserIdAndBookId(Long userId, Long bookId) {
        if (!userBookRepository.existsByUserIdAndBookId(userId, bookId))
            throw new UserBookNotFoundException(userId, bookId);
    
        userBookRepository.deleteByUserIdAndBookId(userId, bookId);
    }
}
