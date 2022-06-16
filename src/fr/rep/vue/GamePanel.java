package fr.rep.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import fr.rep.controler.Controler;
import fr.rep.model.Score;
import fr.rep.observer.Observable;
import fr.rep.observer.Observer;

public class GamePanel extends ZContainer implements Observer {
	
	private Controler controler;
	private String motCache = "", imgPath = "image\\pendu0.jpg";
	private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private List<JButton> bLettres;
	
	private int nbMots = 0, score = 0;
	
	
	private JPanel panLettres, 
				   panG = new JPanel(),
				   panD = new JPanel();
	
	private ImageLabel imgL;
	private JLabel labNbMots = new JLabel("Nombre de mots trouvés : " + nbMots),
				   labScore = new JLabel("Votre score actuel est de : " + score),
				   labMot = new JLabel(motCache);
	
	private BoutonListener bl = new BoutonListener();
	
	public GamePanel(Dimension dim, Observable model){
		super(dim);
		this.controler = new Controler(model);
		model.notifyObserver();
		initPanel();
	}
	
	protected void initPanel(){
		initClavier();
		initPanneau();
		
	}
	
	private void initClavier(){
		bLettres = new ArrayList<JButton>();
		for(String lettre : alphabet){
			JButton b = new JButton(lettre);
			b.setMinimumSize(new Dimension(45, 30));
			b.addActionListener(bl);
			bLettres.add(b);
		}
		panLettres = new JPanel();
		panLettres.setBackground(Color.white);
		panLettres.setPreferredSize(new Dimension(380, 200));
		
		
		GridLayout gl = new GridLayout(4, 7);
		gl.setHgap(5);
		gl.setVgap(5);
		
		panLettres.setLayout(gl);
		panLettres.setBackground(Color.white);
		for(int i = 0; i < 26; i++){
			panLettres.add(bLettres.get(i));
			if(i == 20){
				JButton butt = new JButton();
				butt.setVisible(false);
				panLettres.add(butt);
			}
		}
	}
	
	private void initPanneau(){
		panG.removeAll();
		panD.removeAll();
		labNbMots = new JLabel("Nombre de mots trouvés : " + nbMots);
		labScore = new JLabel("Votre score actuel est de : " + score);
		labMot = new JLabel(motCache);
		imgL = new ImageLabel(imgPath);
		imgL.setPreferredSize(new Dimension(350, 320));
		panG.setPreferredSize(new Dimension(500, 400));
		panG.setBackground(Color.white);
		Dimension d = new Dimension(400, 30);
		labNbMots.setPreferredSize(d);
		labNbMots.setHorizontalAlignment(JLabel.CENTER);
		labScore.setPreferredSize(d);
		labScore.setHorizontalAlignment(JLabel.CENTER);
		labMot.setPreferredSize(new Dimension(500, 60));
		labMot.setFont(comic40);
		labMot.setForeground(Color.blue);
		labMot.setHorizontalAlignment(JLabel.CENTER);
		
		panD.setBackground(Color.white);
		panD.setPreferredSize(new Dimension(350, 320));
		panG.add(labNbMots);
		panG.add(labScore);
		panG.add(labMot);
		panG.add(panLettres);
	
		panD.add(imgL);
		
		panel.add(panG);
		panel.add(panD);
	}
	
	public void update(String mot, int points, String imgPath, int nbMots){
		labMot.setText(mot);
		labScore.setText("Votre score actuel est de : " + points + " point" 
				+ ((points > 1) ? "s." : "."));
		imgL.setImagePath(imgPath);
		labNbMots.setText("Nombre de mots trouvés : " + nbMots);
	}

	public void restart(String word){
		for(JButton butt : bLettres)
			butt.setEnabled(true);
		
		imgL.setImagePath("image\\pendu0.jpg");
		labMot.setText(word);
	}
	
	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			((JButton)e.getSource()).setEnabled(false);
			controler.control(((JButton)e.getSource()).getText().charAt(0));
		}
	}
	public void showScore(Score[] scores){}
	
	public void accueil(){}

}
