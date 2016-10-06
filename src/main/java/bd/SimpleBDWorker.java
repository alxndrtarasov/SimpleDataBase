package bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bd.add.Adder;
import bd.backup.Backuper;
import bd.change.Changer;
import bd.delete.Deleter;
import bd.find.Finder;
import bd.imp.Importer;
import javassist.NotFoundException;
import objtype.Obj;

public class SimpleBDWorker implements BDWorker {

	private String fileName;
	private List<Obj> allObjects;
	private int lastId;
	private Adder adder;
	private Deleter deleter;
	private Changer changer;
	private Finder finder;
	private Backuper backuper;
	private Importer importer;

	public Deleter getDeleter() {
		return deleter;
	}

	public void setDeleter(Deleter deleter) {
		this.deleter = deleter;
	}

	public Changer getChanger() {
		return changer;
	}

	public void setChanger(Changer changer) {
		this.changer = changer;
	}

	public Finder getFinder() {
		return finder;
	}

	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	public Backuper getBackuper() {
		return backuper;
	}

	public void setBackuper(Backuper backuper) {
		this.backuper = backuper;
	}

	public Importer getImporter() {
		return importer;
	}

	public void setImporter(Importer importer) {
		this.importer = importer;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public SimpleBDWorker(String fileName) {
		this.fileName = fileName;

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
		allObjects = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inlineData = line.split(";");
				Obj o = new Obj(Integer.parseInt(inlineData[0]), inlineData[1], df.parse(inlineData[2]), inlineData[3]);
				allObjects.add(o);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastId = allObjects.isEmpty() ? 0 : allObjects.get(allObjects.size() - 1).getId();
		return allObjects;
	}

	@Override
	public void add(Obj o) {
		adder.add(o, fileName);
	}

	@Override
	public Obj deleteById(int id) {
		return deleter.deleteById(id, fileName);
	}

	@Override
	public void change(int id, String fieldName, String fieldValue) {
		changer.change(id, fieldName, fieldValue, fileName);
	}

	@Override
	public void backup(String fileName) {
		backuper.backup(fileName, this.fileName);
	}

	@Override
	public void reestablishFrom(String fileName) {
		backuper.reestablishFrom(fileName, this.fileName);
	}

	@Override
	public void importTo(String fileName) {
		importer.importTo(fileName, this.fileName);
	}

	@Override
	public int getLastId() {
		return lastId;
	}

	public Adder getAdder() {
		return adder;
	}

	public void setAdder(Adder adder) {
		this.adder = adder;
	}

	@Override
	public List<Obj> delete(String fieldName, String fieldValue) {
		return deleter.delete(fieldName, fieldValue, fileName);
	}

	@Override
	public Obj findById(int id) throws NotFoundException {
		return finder.findById(id, fileName);
	}

	@Override
	public List<Obj> find(String fieldName, String field) throws NotFoundException {
		return finder.find(fieldName, field, fileName);
	}

}
