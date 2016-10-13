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
			String val = hash1.get("" + Math.abs(o.getName().hashCode() % 100)/* hashSize */);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash1.put("" + (Math.abs(o.getName().hashCode() % 100)/* hashSize */), val);

			HashMap<String, String> hash2 = ((SimpleBDWorker) worker).getHash2();
			val = hash2.get("" + Math.abs(o.getDate().hashCode() % 100)/* hashSize */);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash2.put("" + (Math.abs(o.getDate().hashCode() % 100)/* hashSize */), val);

			HashMap<String, String> hash3 = ((SimpleBDWorker) worker).getHash3();
			val = hash3.get("" + Math.abs(o.getDescription().hashCode() % 100)/* hashSize */);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hash3.put("" + (Math.abs(o.getDescription().hashCode() % 100)/* hashSize */), val);

			HashMap<String, String> hashId = ((SimpleBDWorker) worker).getIdHash();
			val = hashId.get("" + Math.abs(Integer.parseInt(o.getId()) % 100)/* hashSize */);
			if (val != null) {
				val += o.getId() + ";";
			} else {
				val = o.getId() + ";";
			}
			hashId.put("" + Math.abs(Integer.parseInt(o.getId()) % 100), val);

			((SimpleBDWorker) worker).rewriteHash();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
