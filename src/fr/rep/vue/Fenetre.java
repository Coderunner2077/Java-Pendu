package fr.rep.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.rep.model.Model;
import fr.rep.model.Score;
import fr.rep.observer.Observable;
import fr.rep.observer.Observer;

public class Fenetre extends JFrame implements Observer {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu	fichier = new JMenu("Fichier"),
					aPropos = new JMenu("A propos");
	
	private JMenuItem	nouveau = new JMenuItem("Nouveau"),
						score = new JMenuItem("Score"),
						regles = new JMenuItem("Règles"),
						aProposItem = new JMenuItem("Tout à propos du pendu");
	
	private JPanel content = new JPanel();
	private AccueilPanel accueil;
	
	private Observable model;
	
	private Dimension dim;
	
	public Fenetre(Observable model){
		this.setTitle("LE PENDU !");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		dim = new Dimension(this.getWidth(), this.getHeight());
		this.model = (Model)model;
		this.model.addObserver(this);
		initMenu();
		content.setLayout(new BorderLayout());
		accueil = new AccueilPanel(dim);
		content.add(accueil.getPanel());
		this.setContentPane(content);
		this.setVisible(true);
	}
	
	private void initModel(){
		model = new Model();
		model.addObserver(this);
	}
	
	private void initMenu(){
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				GamePanel gp = new GamePanel(dim, model);
				model.addObserver(gp);
				initModel();
				model.notifyObserver();
				content.add(gp.getPanel(), BorderLayout.CENTER);
				content.revalidate();
			}
		});
		regles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				content.add(new RulesPanel(dim).getPanel(), BorderLayout.CENTER);
				content.revalidate();
				model.reset();
			}
		});
		
		score.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				content.add(new ScorePanel(dim, model.getScores()).getPanel());
				content.revalidate();
				model.reset();
			}
		});
		
		aProposItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				content.add(new PanPropos());
				content.revalidate();
				model.reset();
			}
		});
		fichier.setMnemonic('f');
		fichier.add(nouveau);
		fichier.add(score);
		fichier.add(regles);
		
		aPropos.add(aProposItem);
		
		menuBar.add(fichier);
		menuBar.add(aPropos);
		
		this.setJMenuBar(menuBar);
	}
	
	public void update(String mot, int points, String imgPath, int nbMots){}
	
	public void restart(String word){}
	
	public void accueil(){
		content.removeAll();
		content.add(new AccueilPanel(dim).getPanel(), BorderLayout.CENTER);
		content.revalidate();
		
	}
	
	public void showScore(Score[] score){
		content.removeAll();
		content.add(new ScorePanel(dim, score).getPanel());
		content.revalidate();
	}
}
