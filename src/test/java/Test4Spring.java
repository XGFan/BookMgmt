import com.bean.supplier.Supplier;
import com.dao.BkpurchaseDAO;
import com.dao.ClassDAO;
import com.service.BookService;
import com.service.ClassService;
import com.service.CourseBookViewService;
import com.service.SupplierService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * DATE:2014/11/24
 * TIME:20:45
 * Created by guofan on 2014/11/24
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class Test4Spring extends AbstractJUnit4SpringContextTests {
    @Autowired
    BkpurchaseDAO bkpurchaseDAO;
    @Autowired
    ClassService classService;
    @Autowired
    BookService bookService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    CourseBookViewService courseBookViewService;
//    @Autowired
//    SupplierService supplierService;

    public static void cout(Object x) {
        System.out.println(x);
    }

    @org.junit.Test
    public void main() {
//        System.out.println("This is a test!");
//        classdao.findById("2007020101").toString();
//        JSONObject x = JSONObject.fromObject(t);
//        List x =  new ArrayList();
//        x.save(classService.findById("2007020101"));
//        List y = class2List(x);
//        System.out.println(y.toString());
//        JSONArray t = fromObject(y);
//        cout(y);
//        bookService.delete(bookService.findById("14041738021"));

//        cout(getSubList(classService.getAll(), 1, 10).toString());
//        cout(getSubList(classService.getAll(), 1, 10));
//        classService.
//        System.out.println(x);
//        cout(classService.accurateQuery("信息工程学院", "医学信息工程", "2010", "黄"));
//        cout(JSONArray.fromObject(courseBookViewService.getAll()));
//        cout(courseBookViewService.findAllCourse());
//        cout(JSONObject.fromObject(true));
        Supplier t = supplierService.findById("1029");
        cout(t);
        supplierService.delete(t);
    }
}
