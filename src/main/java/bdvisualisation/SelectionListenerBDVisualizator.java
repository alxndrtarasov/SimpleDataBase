package bdvisualisation;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bd.BDWorker;
import objtype.Obj;

public class SelectionListenerBDVisualizator implements BDVisualizator {

	BDWorker worker;

	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	SelectionListenerBDVisualizator(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public JScrollPane getTable() {
		final JTable table;
		Object[] columnTitles = { "id", "name", "date", "description" };
		List<Obj> allObjects = worker.getAllObjects();
		System.out.println(allObjects);
		Object[][] rowData = new Object[allObjects.size()][4];
		for (int i = 0; i < allObjects.size(); i++) {
			Obj currentObj = allObjects.get(i);
			rowData[i][0] = currentObj.getId();
			rowData[i][1] = currentObj.getName();
			rowData[i][2] = currentObj.getDate();
			rowData[i][3] = currentObj.getDescription();
		}
		table = new JTable(rowData, columnTitles);

		table.setCellSelectionEnabled(false);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedData = null;

				int[] selectedRow = table.getSelectedRows();
				int[] selectedColumns = table.getSelectedColumns();

				for (int i = 0; i < selectedRow.length; i++) {
					for (int j = 0; j < selectedColumns.length; j++) {
						selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
					}
				}
				System.out.println("Selected: " + selectedData);
			}

		});

		JScrollPane result = new JScrollPane(table);
		result.setSize(300, 200);
		return result;
	}

	@Override
	public String getFileName() {
		return worker.getFileName();
	}

}
