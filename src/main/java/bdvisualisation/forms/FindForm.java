package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bd.BDWorker;
import bdvisualisation.ContentTable;
import objtype.Obj;

public class FindForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FindForm(BDWorker worker) {
		setLayout(new GridLayout(3, 2));
		JFrame findResult = new JFrame("Results of search");
		JLabel inputLabel = new JLabel("Input:");
		JLabel fieldLabel = new JLabel("Field:");

		JComboBox<String> fieldChooser = new JComboBox<>();

		fieldChooser.addItem("id");
		fieldChooser.addItem("name");
		fieldChooser.addItem("date");
		fieldChooser.addItem("description");

		JTextField input = new JTextField();

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fieldChooser.getSelectedItem().equals("id")) {
					List<Obj> objs = new ArrayList<>();
					try {
						objs.add(worker.findById(Integer.parseInt(input.getText())));
					} catch (NumberFormatException e1) {
						input.setText("Wrong id format");
					}
					findResult.add(new ContentTable(objs));
				} else {
					findResult.add(
							new ContentTable(worker.find((String) fieldChooser.getSelectedItem(), input.getText())));
				}
				findResult.setVisible(true);
			}
		});

		add(fieldLabel);
		add(fieldChooser);
		add(inputLabel);
		add(input);
		add(ok);

		setSize(300, 200);

		setVisible(true);
	}
}
