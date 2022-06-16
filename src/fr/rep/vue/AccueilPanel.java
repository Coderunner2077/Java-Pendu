package fr.rep.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AccueilPanel extends ZContainer  {
	
	private ImageIcon img;
	private JLabel titre;
	
	public AccueilPanel(Dimension dim){
		super(dim);
		initPanel();	
		
	}
	
	protected void initPanel(){
		img = new ImageIcon("image\\accueil.jpg");
		titre = new JLabel("Bienvenue dans le jeu du PENDU", JLabel.CENTER);
		titre.setFont(comic40);
		titre.setPreferredSize(new Dimension(150, 50));
		
		JLabel imageLab = new JLabel(img);
		JTextArea textArea = new JTextArea();
		textArea.setFont(arial);
		String info = "\tVous avez 7 coups pour trouver le mot caché, et si vous réussissez..."
				+ " et bien on recommence !\n";
		info += "\tPlus vous avez trouvé de mots, plus votre score grandira ! Alors à vous de jouer";
		info += "\n\tPROVERBE : \n\t\t\"Pas vu, pas pris ! \n\t\tPris, PENDU !!!!!!\"";
		textArea.setText(info);
		
		textArea.setEditable(false);
		
		panel.setLayout(new BorderLayout());
		panel.add(titre, BorderLayout.NORTH);
		panel.add(imageLab, BorderLayout.CENTER);
		panel.add(textArea, BorderLayout.SOUTH);
	}
}

