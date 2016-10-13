package bd.delete;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import bd.BDWorker;
import bd.SimpleBDWorker;
import objtype.Obj;

public class SimpleDeleter implements Deleter {
	BDWorker worker;

	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public List<Obj> delete(String fieldName, String fieldValue, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Obj deleteById(int id, String fileName) {
		HashMap<String, String> idHash = ((SimpleBDWorker) worker).getIdHash();
		HashMap<String, String> hash1 = ((SimpleBDWorker) worker).getHash1();
		HashMap<String, String> hash2 = ((SimpleBDWorker) worker).getHash2();
		HashMap<String, String> hash3 = ((SimpleBDWorker) worker).getHash3();
		String val = idHash.get("" + Math.abs(id % 100));
		if (val == null) {
			return null;
		} else {
			System.out.println("val for del was found");
			for (String each : val.split(";")) {
				if (each.equals("" + id)) {
					System.out.println("id for del was found");
					RandomAccessFile reader = ((SimpleBDWorker) worker).db;
					try {
						reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1));
						String line = reader.readLine();
						System.out.println(line);
						reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1));
						reader.writeBytes(String.format("%-400s", ""));
						String[] inlineData = line.split(";");
						Obj o = new Obj(inlineData[0].trim(), inlineData[1].trim(),
								((SimpleBDWorker) worker).getDf().parse(inlineData[2].trim()), inlineData[3].trim());

						String key = "" + Math.abs(o.getName().hashCode() % 100)/* hashSize */;
						String value = hash1.get(key);
						System.out.println("was " + value);
						if (value.startsWith(id + ";")) {
							value = value.replaceFirst(id + ";", "");
						} else {
							value = value.replaceAll(";" + id + ";", ";");
						}
						hash1.put(key, value);
						System.out.println("became " + hash1.get(key));
						key = "" + Math
								.abs(((SimpleBDWorker) worker).df.format(o.getDate()).hashCode() % 100)/* hashSize */;
						value = hash2.get(key);
						System.out.println("was " + value);
						if (value.startsWith(id + ";")) {
							value = value.replaceFirst(id + ";", "");
						} else {
							value = value.replaceAll(";" + id + ";", ";");
						}
						hash2.put(key, value);
						System.out.println("became " + hash2.get(key));
						key = "" + Math.abs(o.getDescription().hashCode() % 100)/* hashSize */;
						value = hash3.get(key);
						System.out.println("was " + value);
						if (value.startsWith(id + ";")) {
							value = value.replaceFirst(id + ";", "");
						} else {
							value = value.replaceAll(";" + id + ";", ";");
						}
						hash3.put(key, value);
						System.out.println("became " + hash3.get(key));
						key = "" + Math.abs(Integer.parseInt(o.getId()) % 100)/* hashSize */;
						value = idHash.get(key);
						System.out.println("was " + value);
						if (value.startsWith(id + ";")) {
							value = value.replaceFirst(id + ";", "");
						} else {
							value = value.replaceAll(";" + id + ";", ";");
						}
						idHash.put(key, value);
						System.out.println("became " + idHash.get(key));
						((SimpleBDWorker) worker).rewriteHash();
						return o;

					} catch (IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;
				}
			}
		}
		return null;
	}

}
