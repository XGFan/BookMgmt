import com.dao.ClassDAO;
import com.service.ClassService;
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
public class Obj2Json extends AbstractJUnit4SpringContextTests {
    @Autowired
    ClassDAO classdao;
    @Autowired
    ClassService classService;

    public static void cout(Object x) {
        System.out.println(x);
    }

    @Test
    public void main() {
//        System.out.println("This is a test!");
//        classdao.findById("2007020101").toString();
//        JSONObject x = JSONObject.fromObject(t);
//        List x =  new ArrayList();
//        x.add(classService.findById("2007020101"));
//        List y = class2List(x);
//        System.out.println(y.toString());
//        JSONArray t = fromObject(y);
//        cout(y);

//        cout(getSubList(classService.findAll(), 1, 10).toString());
//        cout(getSubList(classService.findAll(), 1, 10));
//        classService.
//        System.out.println(x);
        cout(classService.accurateQuery("信息工程学院", "医学信息工程", "2010", "黄"));
    }
}
