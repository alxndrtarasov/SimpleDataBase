package starters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bdvisualisation.MainMenu;

public class Start {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		MainMenu mainMenu = (MainMenu) context.getBean("mainMenu");
	}
}
