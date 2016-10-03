package bdvisualisation;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		result.add(visualizator.getTable());
		result.setSize(400, 700);
		return result;
	}

}
