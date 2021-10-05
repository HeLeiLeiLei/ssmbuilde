package com.hl.service;

import com.hl.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookService {
    //addBooks
    int addBook(Books book);
    //deleteBook
    int deleteBook(int bookID);
    //uodateBook
    int updateBook(Books book);
    //queryBook
    Books queryBook(int bookID);
    //queryAllBooks
    List<Books> queryAllBooks(Map map);
    //deleteBooksByIds
    int deleteBooksByIds(Map map);
    //queryBooksNumber
    int queryBooksNumber(Map map);
}
