package fr.rep.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import fr.rep.model.Score;

public class ScorePanel extends ZContainer {
	private Score[] scores;
	private JLabel[] labels;
	private int size = 35;
	private int indice = 0;
	private Font font;
	
	public ScorePanel(Dimension dim, Score[] sc){
		super(dim);
		scores = sc;
		initPanel();
	}
	
	public void setScores(Score[] sc){
		this.scores = sc;
	}
	
	protected void initPanel(){
		labels = new JLabel[scores.length];
		for(Score score : scores){
			font = new Font("Arial", Font.BOLD, size);
			labels[indice] = new JLabel(score.toString());
			labels[indice].setPreferredSize(new Dimension(600, 35));
			labels[indice].setFont(font);
			labels[indice].setHorizontalAlignment(JLabel.CENTER);
			panel.add(labels[indice]);
			indice++;
			size--;
		}
	}

}
