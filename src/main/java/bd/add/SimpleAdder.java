package bd.add;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import bd.BDWorker;
import bd.SimpleBDWorker;
import objtype.Obj;

public class SimpleAdder implements Adder {
	BDWorker worker;

	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public void add(Obj o, String fileName) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			FileWriter sw = new FileWriter(fileName, true);
			sw.write(String.format("%-99s", ("" + o.getId())) + ";" + String.format("%-99s", o.getName()) + ";"
					+ String.format("%-99s", df.format(o.getDate())) + ";" + String.format("%-99s", o.getDescription())
					+ ";\n");
			sw.close();

			HashMap<String, String> hash1 = ((SimpleBDWorker) worker).getHash1();
			String key = "" + Math.abs(o.getName().hashCode() % 100)/* hashSize */;
			String val = hash1.get(key);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash1.put(key, val);

			HashMap<String, String> hash2 = ((SimpleBDWorker) worker).getHash2();
			key = "" + Math.abs(((SimpleBDWorker) worker).df.format(o.getDate()).hashCode() % 100)/* hashSize */;
			val = hash2.get(key);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash2.put(key, val);

			HashMap<String, String> hash3 = ((SimpleBDWorker) worker).getHash3();
			key = "" + Math.abs(o.getDescription().hashCode() % 100)/* hashSize */;
			val = hash3.get(key);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash3.put(key, val);

			HashMap<String, String> hashId = ((SimpleBDWorker) worker).getIdHash();
			key = "" + Math.abs(Integer.parseInt(o.getId()) % 100)/* hashSize */;
			val = hashId.get(key);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hashId.put(key, val);

			((SimpleBDWorker) worker).rewriteHash();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
