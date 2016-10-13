package bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bd.add.Adder;
import bd.backup.Backuper;
import bd.change.Changer;
import bd.delete.Deleter;
import bd.find.Finder;
import bd.imp.Importer;
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
	private HashMap<String, String> idHash = new HashMap<>();
	private HashMap<String, String> hash1 = new HashMap<>();
	private HashMap<String, String> hash2 = new HashMap<>();
	private HashMap<String, String> hash3 = new HashMap<>();
	private int colSize;
	public RandomAccessFile db;
	public RandomAccessFile db1;
	public RandomAccessFile db2;
	public RandomAccessFile db3;
	public RandomAccessFile dbId;
	public SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public int hashSize = 100;

	public void rewriteHash() {
		try {
			File fileId = new File(fileName + "_ids");
			if (!fileId.exists()) {
				fileId.createNewFile();
			}
			FileWriter idWriter = new FileWriter(fileId);
			for (String currentKey : idHash.keySet()) {
				idWriter.write(currentKey + ">>" + idHash.get(currentKey) + "\n");
			}
			idWriter.close();

			File file1 = new File(fileName + "1");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			FileWriter writer1 = new FileWriter(file1);
			for (String currentKey : hash1.keySet()) {
				writer1.write(currentKey + ">>" + hash1.get(currentKey) + "\n");
			}
			writer1.close();

			File file2 = new File(fileName + "2");
			if (!file2.exists()) {
				file2.createNewFile();
			}
			FileWriter writer2 = new FileWriter(file2);
			for (String currentKey : hash2.keySet()) {
				writer2.write(currentKey + ">>" + hash2.get(currentKey) + "\n");
			}
			writer2.close();

			File file3 = new File(fileName + "3");
			if (!file3.exists()) {
				file3.createNewFile();
			}
			FileWriter writer3 = new FileWriter(file3);
			for (String currentKey : hash3.keySet()) {
				writer3.write(currentKey + ">>" + hash3.get(currentKey) + "\n");
			}

			writer3.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SimpleDateFormat getDf() {
		return df;
	}

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
		try {
			// FileWriter fwId = new FileWriter(new File("ids_" + fileName));
			// for (int i = 0; i < 100/* hashSize */; i++) {
			// fwId.write(i + ">>\n");
			// }
			// FileWriter fw1 = new FileWriter(new File("1" + fileName));
			// for (int i = 0; i < 100/* hashSize */; i++) {
			// fw1.write(i + ">>\n");
			// }
			// FileWriter fw2 = new FileWriter(new File("2" + fileName));
			// for (int i = 0; i < 100/* hashSize */; i++) {
			// fw2.write(i + ">>\n");
			// }
			// FileWriter fw3 = new FileWriter(new File("3" + fileName));
			// for (int i = 0; i < 100/* hashSize */; i++) {
			// fw3.write(i + ">>\n");
			// }
			// fwId.close();
			// fw1.close();
			// fw2.close();
			// fw3.close();
			File fileId = new File(fileName + "_ids");
			if (!fileId.exists()) {
				fileId.createNewFile();
			}
			BufferedReader idReader = new BufferedReader(new FileReader(fileId));
			String line = "";
			while ((line = idReader.readLine()) != null) {
				if (line.split(">>").length > 1) {
					idHash.put(line.split(">>")[0], line.split(">>")[1]);
				}
			}
			idReader.close();

			File file1 = new File(fileName + "1");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			BufferedReader firstReader = new BufferedReader(new FileReader(file1));
			while ((line = firstReader.readLine()) != null) {
				if (line.split(">>").length > 1) {
					hash1.put(line.split(">>")[0], line.split(">>")[1]);
				}
			}
			firstReader.close();

			File file2 = new File(fileName + "2");
			if (!file2.exists()) {
				file2.createNewFile();
			}
			BufferedReader secondReader = new BufferedReader(new FileReader(file2));
			while ((line = secondReader.readLine()) != null) {
				if (line.split(">>").length > 1) {
					hash2.put(line.split(">>")[0], line.split(">>")[1]);
				}
			}
			secondReader.close();

			File file3 = new File(fileName + "3");
			if (!file3.exists()) {
				file3.createNewFile();
			}
			BufferedReader thirdReader = new BufferedReader(new FileReader(file3));
			while ((line = thirdReader.readLine()) != null) {
				if (line.split(">>").length > 1) {
					hash3.put(line.split(">>")[0], line.split(">>")[1]);
				}
			}
			thirdReader.close();
			db = new RandomAccessFile(fileName, "rw");
			dbId = new RandomAccessFile(fileId, "rw");
			db1 = new RandomAccessFile(file1, "rw");
			db2 = new RandomAccessFile(file2, "rw");
			db3 = new RandomAccessFile(file3, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("No such file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getIdHash() {
		return idHash;
	}

	public HashMap<String, String> getHash1() {
		return hash1;
	}

	public HashMap<String, String> getHash2() {
		return hash2;
	}

	public HashMap<String, String> getHash3() {
		return hash3;
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

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			lastId = 0;
			while ((line = reader.readLine()) != null) {
				lastId++;
				String[] inlineData = line.split(";");
				if (inlineData.length > 1) {
					Obj o = new Obj(inlineData[0].trim(), inlineData[1].trim(), df.parse(inlineData[2].trim()),
							inlineData[3].trim());
					allObjects.add(o);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allObjects;
	}

	@Override
	public void add(Obj o) {
		adder.add(o, fileName);
		lastId++;
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
	public Obj findById(int id) {
		return finder.findById(id, fileName);
	}

	@Override
	public List<Obj> find(String fieldName, String field) {
		return finder.find(fieldName, field, fileName);
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

}
