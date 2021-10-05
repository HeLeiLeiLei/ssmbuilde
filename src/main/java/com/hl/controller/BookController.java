package com.hl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hl.pojo.Books;
import com.hl.service.BookService;
import com.hl.utils.PageUtils;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.ibatis.annotations.Insert;
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
        map.put("startIndex",1);
        map.put("pageSize",5);
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

    @RequestMapping("/toDeleteBooks")
    @ResponseBody
    public List<Books> deletBooks(String ids[],Model model){
        List<String> idList= new ArrayList<String>();
        Map map = new HashMap();
        if (ids != null && ids.length>0){
                for (String id : ids) {
                    idList.add(id);
                }
                map.put("list",idList);
                if(service.deleteBooksByIds(map)>0){
                    HashMap map1 = new HashMap();
                    List<Books> books = service.queryAllBooks(map1);
                    return books;
                }
        }
        return null;
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
    public List<Books> queryBook(String queryBookName,
                                 @RequestParam(defaultValue ="1")String CrrentPage,
                                 @RequestParam(defaultValue = "5") String pageSize){
        System.out.println("进入到queryBook");
        HashMap map = new HashMap();
        map.put("queryBookName","%"+queryBookName+"%");
        //查询出总共有多少条数据
        int totaleNumber = service.queryBooksNumber(map);

        //创建一个分页对象
        PageUtils pageUtils = new PageUtils(Integer.parseInt(CrrentPage),Integer.parseInt(pageSize),totaleNumber);
        //设置当前页的值
        pageUtils.setCrrentPage(Integer.parseInt(CrrentPage));
        //设置查询的起使索引
        pageUtils.setStartIndex(Integer.parseInt(CrrentPage));

        //最后把值封装到万能Map中
        map.put("startIndex",pageUtils.getStartIndex());
        map.put("pageSize",pageUtils.getPageSize());

        System.out.println(map.get("startIndex"));
        System.out.println(map.get("pageSize"));
        List<Books> books = service.queryAllBooks(map);
        return books;
    }

}
