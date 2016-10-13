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
	private int hashSize;

	public int getHashSize() {
		return hashSize;
	}

	public void setHashSize(int hashSize) {
		this.hashSize = hashSize;
	}

	public BDOpenner getOpenner() {
		return openner;
	}

	public void setOpenner(BDOpenner openner) {
		this.openner = openner;
	}

	MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLayout(new GridLayout(1, 1));
		JLabel label = new JLabel("Enter path to DataBase here:");
		JTextField bdPath = new JTextField();
		JButton defStart = new JButton("Start DB");
		defStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame baseFrame = openner.open();
				baseFrame.setVisible(true);
				// try {
				// FileWriter fwId = new FileWriter(new File("ids_" +
				// defDbPath));
				// for (int i = 0; i < hashSize; i++) {
				// fwId.write(i + ">>\n");
				// }
				// FileWriter fw1 = new FileWriter(new File("1" + defDbPath));
				// for (int i = 0; i < hashSize; i++) {
				// fw1.write(i + ">>\n");
				// }
				// FileWriter fw2 = new FileWriter(new File("2" + defDbPath));
				// for (int i = 0; i < hashSize; i++) {
				// fw2.write(i + ">>\n");
				// }
				// FileWriter fw3 = new FileWriter(new File("3" + defDbPath));
				// for (int i = 0; i < hashSize; i++) {
				// fw3.write(i + ">>\n");
				// }
				// fwId.close();
				// fw1.close();
				// fw2.close();
				// fw3.close();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}

		});

		JButton start = new JButton("Start DB");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});
		// add(label);
		// add(bdPath);
		// add(start);
		add(defStart);
		setVisible(true);
	}

}
