package com.hl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hl.pojo.Books;
import com.hl.service.BookService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;
    public void setService(BookService service) {
        this.service = service;
    }

    @RequestMapping("/q")
    public String queryAllBooks(Model model){
        Map map = new HashMap();
        List<Books> booksList = service.queryAllBooks(map);
        model.addAttribute("list",booksList);
        return "hello";
    }
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBook";
    }

    @PostMapping("/AddBook")
    public String addBook(Books book,Model model){
        int i = service.addBook(book);
        if(i>0){
            return "redirect:/book/q";
        }
        model.addAttribute("msg","添加失败");
        return "redirect:/book/toAddBook";
    }

    //
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("bookID") int bookID,Model model){
        Books books = service.queryBook(bookID);
        model.addAttribute("books",books);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(Books books,Model model){
        int i=service.updateBook(books);
        if(i<=0){
            model.addAttribute("msg","修改失败");
            return "updateBook";
        }
        return "redirect:/book/q";
    }

    @PostMapping("/toDeleteBooks")
    public String deletBooks(String ids[],Model model){
        List<String> idList= new ArrayList<String>();
        Map map = new HashMap();
        if (ids != null && ids.length>0){
            for (String id : ids) {
                idList.add(id);
            }
            map.put("list",idList);
            if(service.deleteBooksByIds(map)>0){
                return "redirect:/book/q";
            }
        }
        model.addAttribute("msg","未选择");
        return "redirect:/book/q";
    }

    @RequestMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable int id){
        if(id>0){
            int i = service.deleteBook(id);
            if(i>0){
                return "redirect:/book/q";
            }
        }
        return "redirect:/book/q";
    }

    @RequestMapping("/queryBook")
    @ResponseBody
    public String queryBook(String queryBookName){
        HashMap map = new HashMap();
        map.put("queryBookName","%"+queryBookName+"%");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Books> books = service.queryAllBooks(map);
        try {
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
