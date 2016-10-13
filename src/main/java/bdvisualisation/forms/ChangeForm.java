package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import bd.BDWorker;
import objtype.Obj;

public class ChangeForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int currentId;

	public ChangeForm(BDWorker worker) {
		setLayout(new GridLayout(6, 1));

		JLabel idLabel = new JLabel("Id:");
		JLabel nameLabel = new JLabel("Field:");
		JLabel dateLabel = new JLabel("Date:");
		JLabel descriptionLabel = new JLabel("Description:");

		JTextField id = new JTextField();
		JTextField name = new JTextField();
		JDateChooser date = new JDateChooser();

		JComboBox<String> description = new JComboBox<>();
		description.addItem("Fruit");
		description.addItem("Vegetable");

		JPanel idPanel = new JPanel();
		idPanel.setLayout(new GridLayout(1, 2));
		idPanel.add(idLabel);
		idPanel.add(id);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2));
		namePanel.add(nameLabel);
		namePanel.add(name);

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(1, 2));
		datePanel.add(dateLabel);
		datePanel.add(date);

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new GridLayout(1, 2));
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(description);

		name.setEditable(false);
		date.setEnabled(false);
		description.setEnabled(false);

		JButton find = new JButton("Find to change");
		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Obj currentObj = worker.findById(Integer.parseInt(id.getText()));
					currentId = Integer.parseInt(currentObj.getId());
					name.setEditable(true);
					date.setEnabled(true);
					description.setEnabled(true);
					name.setText(currentObj.getName());
					// SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
					date.setDate(currentObj.getDate());
					description.setSelectedItem(currentObj.getDescription());
				} catch (NumberFormatException e1) {
					id.setText("Wrong id formad");
				}

			}
		});

		JButton ok = new JButton("Save changes");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker.change(currentId, "name", name.getText());
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				worker.change(currentId, "date", df.format(date.getDate()));
				worker.change(currentId, "description", description.getSelectedItem().toString());
			}
		});

		add(idPanel);
		add(find);
		add(namePanel);
		add(datePanel);
		add(descriptionPanel);
		add(ok);
		setSize(200, 300);
		setVisible(true);
	}
}
