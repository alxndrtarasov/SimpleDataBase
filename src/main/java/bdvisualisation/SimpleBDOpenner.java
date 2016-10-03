package bdvisualisation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleBDOpenner implements BDOpenner {

	BDVisualizator visualizator;

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
		result.add(visualizator.getTable(), BorderLayout.CENTER);
		JButton add = new JButton("Add Obj");
		JPanel buttons = new JPanel(new GridLayout(2, 1));
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddForm(visualizator.getWorker());
			}
		});

		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				result.remove(0);
				result.add(visualizator.getTable(), BorderLayout.CENTER);
			}
		});

		buttons.add(add);
		buttons.add(refresh);

		result.add(buttons, BorderLayout.SOUTH);
		result.setSize(400, 700);
		return result;
	}

}
