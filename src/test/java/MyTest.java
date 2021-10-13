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
import java.util.UUID;

public class MyTest {

    @Test
    public void Test(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);
    }

}
