import com.dao.BookDAO;
import com.dao.CourseDAO;
import com.service.*;
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
//    @Autowired
//    BkpurchaseDAO bkpurchaseDAO;
    @Autowired
    ClassService classService;
    @Autowired
    BookService bookService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    BookPurchaseService bookPurchaseService;
    @Autowired
    CorplanService corplanService;
    @Autowired
    CollegeService collegeService;
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
//        Supplier t = supplierService.findById("1029");
//        cout(t);
//        supplierService.delete(t);
//        cout(JSONArray.fromObject(courseDAO.getCourseByColMajorSem("信息工程学院", "信息管理与信息系统", "1")));
//        cout(bookService.findById("14041700168").toString());
//        JsonConfig config = new JsonConfig();
//        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//        config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
//        String[] excludes = {"bkpurchases","coursebks","books"};
//        config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
//        config.setExcludes(excludes);
//        List<Book> book = bookService.searchByISBN("1");
//        List<Book> books = bookService.getAll();
//        List bk = bookService.test();
//        cout(JSONObject.fromObject(book.get(0),config));
//        cout(JSONArray.fromObject((books.get(0)),config));
//        cout(JSONArray.fromObject(bk.get(0),config));
//        cout(corplanService.findCorplanByColMajorSem("信息工程学院", "医学信息工程","1").get(0));
        System.out.println(collegeService.getNewID("信息工程学院"));
    }
//    @org.junit.Test
    public void test(){
        int[] x = {2,4,5,1,6};
        bubbleswap(x, 5);
        for (int i : x) {
            System.out.println(i);

        }
    }
    private void bubbleswap(int mf[],int nf)
    {
        int temp=0;
        if(nf==0)//传入的为需要排序的项数
        {
            return;//不需要排序的时候退出
        }
        for(int i=0;i<nf-1;i++)
        {//选出nf个数中最大的一个，排到最后
            if(mf[i]>mf[i+1])
            {
                temp=mf[i];
                mf[i]=mf[i+1];
                mf[i+1]=temp;
            }
        }
        bubbleswap(mf,nf-1);//由于本轮已选出最大的一个，下一轮只需要选出剩下的nf-1个数中最大的一个
    }

}
