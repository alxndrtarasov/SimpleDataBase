package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	public AddForm(BDWorker worker) {
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
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				try {
					worker.add(new Obj(worker.getLastId() + 1, name.getText(), df.parse(df.format(date.getDate())),
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
