import com.dao.ClassDAO;
import com.service.ClassService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static com.util.ConvertUtils.class2List;
import static com.util.GetPaginationInfo.getSubList;
import static net.sf.json.JSONArray.fromObject;

/**
 * DATE:2014/11/24
 * TIME:20:45
 * Created by guofan on 2014/11/24
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class Obj2Json extends AbstractJUnit4SpringContextTests {
    @Autowired
    ClassDAO classdao;
    @Autowired
    ClassService classService;

    @Test
    public void main(){
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
        cout(classService.accurateQuery("信息工程学院","医学信息工程","2010","黄"));
    }
    public static void cout(Object x){
        System.out.println(x);
    }
}
