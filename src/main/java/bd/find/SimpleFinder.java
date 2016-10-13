package bd.find;

import java.util.List;

import bd.BDWorker;
import javassist.NotFoundException;
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
	public Obj findById(int id, String fileName) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Obj> find(String fieldName, String field, String fileName) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
