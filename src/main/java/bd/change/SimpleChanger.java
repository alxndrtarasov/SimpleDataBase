package bd.change;

import bd.BDWorker;

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
		// TODO Auto-generated method stub

	}

}
