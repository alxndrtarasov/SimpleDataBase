package bd.change;

import java.io.IOException;
import java.io.RandomAccessFile;

import bd.BDWorker;
import bd.SimpleBDWorker;

public class SimpleChanger implements Changer {
	BDWorker worker;

	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public void change(int id, String fieldName, String fieldValue, String fileName) {
		RandomAccessFile reader = ((SimpleBDWorker) worker).db;
		try {
			switch (fieldName) {
			case "name":
				reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1)
						+ ((SimpleBDWorker) worker).hashSize);
				reader.writeBytes(String.format("%-99s", fieldValue));
				break;
			case "date":
				reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1)
						+ 2 * ((SimpleBDWorker) worker).hashSize);
				reader.writeBytes(String.format("%-99s", fieldValue));
				break;
			case "description":
				reader.seek((id - 1) * (4 * ((SimpleBDWorker) worker).getColSize() + 1)
						+ 3 * ((SimpleBDWorker) worker).hashSize);
				reader.writeBytes(String.format("%-99s", fieldValue));
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
