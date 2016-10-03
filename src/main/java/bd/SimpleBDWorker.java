package bd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import objtype.Obj;

public class SimpleBDWorker implements BDWorker {

	private String fileName;
	private List<Obj> allObjects;
	private int lastId;

	@Override
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public SimpleBDWorker(String fileName) {
		this.fileName = fileName;
		allObjects = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inlineData = line.split(";");
				Obj o = new Obj(Integer.parseInt(inlineData[0]), inlineData[1], new Date(), inlineData[3]);
				allObjects.add(o);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastId = allObjects.isEmpty() ? 0 : allObjects.get(allObjects.size() - 1).getId();
	}

	@Override
	public int getObjectsCount() {
		if (allObjects == null)
			return 0;
		else
			return allObjects.size();
	}

	@Override
	public List<Obj> getAllObjects() {
		return allObjects;
	}

	@Override
	public void add(Obj o) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
			writer.write(o.getId() + ";" + o.getName() + ";" + o.getDate() + ";" + o.getDescription() + "\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Obj> delete(String fieldName, String fieldValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Obj deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void change(int id, String fieldName, String fieldValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void backup(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reestablishFrom(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void importTo(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() {
		return lastId;
	}

}
