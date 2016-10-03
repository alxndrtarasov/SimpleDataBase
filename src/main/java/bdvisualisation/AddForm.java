package bdvisualisation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import bd.BDWorker;
import objtype.Obj;

public class AddForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AddForm(BDWorker worker) {
		setLayout(new GridLayout(4, 2));

		JLabel nameLabel = new JLabel("name:");
		JLabel dateLabel = new JLabel("date:");
		JLabel descriptionLabel = new JLabel("description:");

		JTextField name = new JTextField();
		JDateChooser date = new JDateChooser();
		JComboBox<String> description = new JComboBox<>();

		description.addItem("Fruit");
		description.addItem("Vegetable");

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker.add(new Obj(worker.getLastId() + 1, name.getText(), date.getDate(),
						description.getSelectedItem().toString()));
				name.setText("");
			}
		});

		add(nameLabel);
		add(name);
		add(dateLabel);
		add(date);
		add(descriptionLabel);
		add(description);
		add(ok);

		setSize(300, 200);

		setVisible(true);
	}
}
