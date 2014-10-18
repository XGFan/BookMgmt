package test;
import com.priInfo.course.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
CourseService cor=(CourseServiceImp)ctx.getBean("courseService");

//System.out.print(cor.findByCol("信息工程学院"));
cor.addNewCourse("", "体育部","美容养生", "345", "1");
	}

}
