package bdvisualisation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MainMenu t1000 = (MainMenu) context.getBean("mainMenu");
	}
}
