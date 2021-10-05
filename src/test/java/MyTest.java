import com.hl.controller.BookController;
import com.hl.dao.BookMapper;
import com.hl.pojo.Books;
import com.hl.service.BookService;
import com.hl.service.BookServiceImpl;
import com.hl.utils.PageUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;

public class MyTest {

    @Test
    public void Test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");

    }

}
