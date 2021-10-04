package com.hl.dao;

import com.hl.pojo.Books;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BookMapper {
    //addBooks
    int addBook(Books book);
    //deleteBook
    int deleteBook(@Param("id") int bookID);
    //uodateBook
    int updateBook(Books book);
    //queryBook
    Books queryBook(@Param("id")int bookID);
    //queryAllBooks
    List<Books> queryAllBooks(Map map);
    //deleteBooksByIds
    int deleteBooksByIds(Map map);
}
