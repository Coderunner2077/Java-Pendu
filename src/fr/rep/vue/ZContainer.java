package fr.rep.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class ZContainer {
	protected JPanel panel;
	protected Font comic30 = new Font("Comic Sans MS", Font.BOLD, 30);
	protected Font comic40 = new Font("Comic Sans MS", Font.BOLD, 40);
	protected Font comic20 = new Font("Comic Sans MS", Font.BOLD, 20);
	protected Font arial = new Font("Arial", Font.BOLD, 15);
	protected Font dialog = new Font("Dialog", Font.BOLD + Font.ITALIC, 15);
	
	public ZContainer(Dimension dim){
		panel = new JPanel();
		panel.setPreferredSize(dim);
		panel.setBackground(Color.white);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	protected abstract void initPanel();
}
