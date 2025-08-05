package com.virtuallibrary.VirtualLibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virtuallibrary.VirtualLibrary.dto.BookDTO;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.AuthorNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.BookIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.TitleNotFoundException;
import com.virtuallibrary.VirtualLibrary.model.Book;
import com.virtuallibrary.VirtualLibrary.repository.BookRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookIdNotFoundException(id));
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        if (books.isEmpty())
            throw new TitleNotFoundException(title);

        return books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);

        if (books.isEmpty()) 
            throw new AuthorNotFoundException(author);

        return books;
    }

    public Book save(BookDTO dto) {
        Book book = Book.builder()
            .title(dto.getTitle())
            .author(dto.getAuthor())
            .genre(dto.getGenre())
            .build();

        return bookRepository.save(book);
    }

    public Book update(Long id, BookDTO dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookIdNotFoundException(id));

        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setGenre(dto.getGenre());

        return bookRepository.save(existingBook);
    }

    public Book updatePartial(Long id, BookDTO dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookIdNotFoundException(id));

        if (StringUtils.isNotBlank(dto.getTitle())) {
            existingBook.setTitle(dto.getTitle());
        }
        
        if (StringUtils.isNotBlank(dto.getAuthor())) {
            existingBook.setAuthor(dto.getAuthor());
        }

        if (StringUtils.isNotBlank(dto.getGenre())) {
            existingBook.setGenre(dto.getGenre());
        }

        return bookRepository.save(existingBook);
    }

    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookIdNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
