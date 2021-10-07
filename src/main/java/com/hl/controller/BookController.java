package com.hl.controller;

import com.hl.pojo.Books;
import com.hl.service.BookService;
import com.hl.utils.PageUtils;
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

    @RequestMapping("/toHello")
    public String toHello(Model model){
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
            return "hello";
        }
        model.addAttribute("msg","添加失败");
        return "hello";
    }

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
        return "hello";
    }

    @RequestMapping("/toDeleteBooks")
    @ResponseBody
    public Map deletBooks(String ids[],Model model){
        List<String> idList= new ArrayList<String>();
        Map map = new HashMap();
        if (ids != null && ids.length>0){
            for (String id : ids) {
                idList.add(id);
            }
            map.put("list",idList);
            if(service.deleteBooksByIds(map)>0){
                map.put("infor","success");
            }else {
                map.put("infor","fail");
            }
            return map;
        }
        return map;
    }

    @RequestMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable int id){
        if(id>0){
            int i = service.deleteBook(id);
            if(i>0){
                return "hello";
            }
        }
        return "hello";
    }

    @RequestMapping("/queryBook")
    @ResponseBody
    public Map queryBook(String queryBookName,
                                 @RequestParam(defaultValue ="1")String CrrentPage,
                                 @RequestParam(defaultValue = "5") String pageSize){
        System.out.println("进入到queryBook");
        HashMap map = new HashMap();
        if(queryBookName != null && queryBookName.length()>0){
            map.put("queryBookName","%"+queryBookName+"%");
        }

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

        List<Books> books = service.queryAllBooks(map);
        HashMap hashMap = new HashMap();
        hashMap.put("pageUtils",pageUtils);
        hashMap.put("list",books);
        return hashMap;
    }

}
