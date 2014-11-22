package test;

import com.dao.CollegeDAO;
import com.action.ColAction;
import com.util.ConvertUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCollege {

    public static void main(String args[]) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ColAction clt = (ColAction) ctx.getBean("collegeAction");
        CollegeDAO clgDao = (CollegeDAO) ctx.getBean("collegeDAO");
        //clt.list();//College(String idcm, String col, String major, Integer semnum)
        //College clg = new College("liub","liub","liub",9);
        //clt.add(clg);
        //clt.del("liub");
        //clg.setCol("liuei");
        //clt.update(clg);
        //clt.query("信息工程学院");
//        clt.accurateQuery();
//        List collist = clgDao.findByMajor("医学信息工程");
        System.out.println(ConvertUtils.ToCollegeList(clgDao.findAll()));
//        System.out.println(collist.size());
        //clt.
        //System.out.println();
    }
}
