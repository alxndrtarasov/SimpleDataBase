package bdvisualisation;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import objtype.Obj;

public class ContentTable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ContentTable(List<Obj> objs) {
		Object[] columnTitles = { "id", "name", "date", "description" };
		System.out.println(objs);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Object[][] rowData = new Object[objs.size()][4];
		for (int i = 0; i < objs.size(); i++) {
			Obj currentObj = objs.get(i);
			rowData[i][0] = currentObj.getId();
			rowData[i][1] = currentObj.getName();
			rowData[i][2] = df.format(currentObj.getDate());
			rowData[i][3] = currentObj.getDescription();
		}
		table = new JTable(rowData, columnTitles);
		this.add(table);
	};
}
