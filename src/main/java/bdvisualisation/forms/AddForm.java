package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import bd.BDWorker;
import objtype.Obj;

public class AddForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddForm(BDWorker worker) {
		setLayout(new GridLayout(4, 2));

		JLabel nameLabel = new JLabel("name:");
		JLabel dateLabel = new JLabel("date:");
		JLabel descriptionLabel = new JLabel("description:");

		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]{0,99}");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JFormattedTextField name = new JFormattedTextField(formatter);
		JDateChooser date = new JDateChooser();
		JComboBox<String> description = new JComboBox<>();

		description.addItem("Fruit");
		description.addItem("Vegetable");

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				try {
					int id = (worker.getLastId() + 1);
					worker.add(new Obj("" + id, name.getText(), df.parse(df.format(date.getDate())),
							description.getSelectedItem().toString()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
