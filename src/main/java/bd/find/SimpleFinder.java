package bd.find;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bd.BDWorker;
import bd.SimpleBDWorker;
import objtype.Obj;

public class SimpleFinder implements Finder {
	BDWorker worker;

	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public Obj findById(int id, String fileName) {
		try {
			RandomAccessFile reader = ((SimpleBDWorker) worker).db;

			reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1));
			String line = reader.readLine();
			System.out.println(line);
			String[] inlineData = line.split(";");
			Obj o = new Obj(inlineData[0].trim(), inlineData[1].trim(),
					((SimpleBDWorker) worker).getDf().parse(inlineData[2].trim()), inlineData[3].trim());
			return o;
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Obj> find(String fieldName, String field, String fileName) {
		List<Obj> result = new ArrayList<>();
		HashMap<String, String> hash = null;
		String val;
		switch (fieldName) {
		case "name":
			hash = ((SimpleBDWorker) worker).getHash1();
			break;
		case "date":
			hash = ((SimpleBDWorker) worker).getHash2();
			break;
		case "description":
			hash = ((SimpleBDWorker) worker).getHash3();
			break;
		}
		val = hash.get("" + Math.abs(field.hashCode() % 100)/* hashSize */);
		for (String each : val.split(";")) {
			Obj suspect = findById(Integer.parseInt(each), fileName);
			switch (fieldName) {
			case "name":
				if (suspect.getName().equals(field)) {
					result.add(suspect);
				}
				break;
			case "date":
				if (((SimpleBDWorker) worker).df.format(suspect.getDate()).equals(field)) {
					result.add(suspect);
				}
				break;
			case "description":
				if (suspect.getDescription().equals(field)) {
					result.add(suspect);
				}
				break;
			}
		}
		return result;
	}

}
