package bdvisualisation;

import javax.swing.JScrollPane;

import bd.BDWorker;

public interface BDVisualizator {
	public JScrollPane getTable();

	public String getFileName();

	public BDWorker getWorker();
}
