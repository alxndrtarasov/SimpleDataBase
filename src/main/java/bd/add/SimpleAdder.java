package bd.add;

import java.io.FileWriter;
import java.text.SimpleDateFormat;

import objtype.Obj;

public class SimpleAdder implements Adder {
	@Override
	public void add(Obj o, String fileName) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			FileWriter sw = new FileWriter(fileName, true);
			sw.write(o.getId() + ";" + o.getName() + ";" + df.format(o.getDate()) + ";" + o.getDescription() + "\n");
			sw.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
