package bdvisualisation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getDefDbPath() {
		return defDbPath;
	}

	public void setDefDbPath(String defDbPath) {
		this.defDbPath = defDbPath;
	}

	private String defDbPath;
	private BDOpenner openner;

	public BDOpenner getOpenner() {
		return openner;
	}

	public void setOpenner(BDOpenner openner) {
		this.openner = openner;
	}

	MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLayout(new GridLayout(4, 1));
		JLabel label = new JLabel("Enter path to DataBase here:");
		JTextField bdPath = new JTextField();
		JButton defStart = new JButton("Start default DB");
		defStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame baseFrame = openner.open();
				baseFrame.setVisible(true);
			}

		});

		JButton start = new JButton("Start DB");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});
		add(label);
		add(bdPath);
		add(start);
		add(defStart);
		setVisible(true);
	}

}
