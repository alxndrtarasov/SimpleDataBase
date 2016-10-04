package bdvisualisation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		JPanel buttons = new JPanel(new GridLayout(2, 1));
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddForm(visualizator.getWorker());
			}
		});

		JButton delete = new JButton("Delete Obj(s)");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DelForm(visualizator.getWorker());
			}
		});
		buttons.add(delete);

		JButton find = new JButton("Delete Obj(s)");
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DelForm(visualizator.getWorker());
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

		buttons.add(add);
		buttons.add(refresh);

		result.add(buttons, BorderLayout.SOUTH);
		result.setSize(400, 700);
		return result;
	}

}
