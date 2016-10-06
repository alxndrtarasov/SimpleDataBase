package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import bd.BDWorker;

public class ReestablishForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReestablishForm(BDWorker worker) {
		setSize(400, 500);
		setLayout(new GridLayout(2, 1));
		JFileChooser path = new JFileChooser();
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker.reestablishFrom(path.getSelectedFile().toString());
			}
		});
		add(path);
		add(ok);
		setVisible(true);
	}

}
