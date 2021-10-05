package com.hl.service;

import com.hl.dao.BookMapper;
import com.hl.pojo.Books;

import java.util.List;
import java.util.Map;


public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }
    public int deleteBook(int bookID) {
        return bookMapper.deleteBook(bookID);
    }

    public int updateBook(Books book) {
        return bookMapper.updateBook(book);
    }

    public Books queryBook(int bookID) {
        return bookMapper.queryBook(bookID);
    }

    public List<Books> queryAllBooks(Map map) {
        return bookMapper.queryAllBooks(map);
    }

    public int deleteBooksByIds(Map map) {
        return bookMapper.deleteBooksByIds(map);
    }

    public int queryBooksNumber(Map map) {
        return bookMapper.queryBooksNumber(map);
    }
}
