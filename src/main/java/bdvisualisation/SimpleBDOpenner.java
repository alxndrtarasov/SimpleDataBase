package bdvisualisation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bdvisualisation.forms.AddForm;
import bdvisualisation.forms.BackupForm;
import bdvisualisation.forms.ChangeForm;
import bdvisualisation.forms.DelForm;
import bdvisualisation.forms.ExportForm;
import bdvisualisation.forms.FindForm;
import bdvisualisation.forms.ReestablishForm;

public class SimpleBDOpenner implements BDOpenner {

	private BDVisualizator visualizator;
	JScrollPane table;

	public BDVisualizator getVisualizator() {
		return visualizator;
	}

	public void setVisualizator(BDVisualizator visualizator) {
		this.visualizator = visualizator;
	}

	@Override
	public JFrame open() {
		JFrame result = new JFrame();
		result.setLayout(new BorderLayout());
		table = visualizator.getTable();
		result.add(table, BorderLayout.CENTER);
		JButton add = new JButton("Add Obj");
		JPanel buttons = new JPanel(new GridLayout(3, 3));
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddForm(visualizator.getWorker());
			}
		});
		buttons.add(add);

		JButton delete = new JButton("Delete Obj(s)");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DelForm(visualizator.getWorker());
			}
		});
		buttons.add(delete);

		JButton find = new JButton("Find Obj(s)");
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FindForm(visualizator.getWorker());
			}
		});
		buttons.add(find);

		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				result.remove(table);
				table = visualizator.getTable();
				result.add(table, BorderLayout.CENTER);
				result.revalidate();
				result.repaint();
			}
		});
		buttons.add(refresh);

		JButton change = new JButton("Change Obj");
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangeForm(visualizator.getWorker());
			}
		});
		buttons.add(change);

		JButton backup = new JButton("Backup to");
		backup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BackupForm(visualizator.getWorker());
			}
		});
		buttons.add(backup);

		JButton reestablish = new JButton("Reestablish from");
		reestablish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReestablishForm(visualizator.getWorker());
			}
		});
		buttons.add(reestablish);

		JButton export = new JButton("Export to");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ExportForm(visualizator.getWorker());
			}
		});
		buttons.add(export);

		result.add(buttons, BorderLayout.SOUTH);
		result.setSize(400, 700);
		return result;
	}

}
