package fr.rep.model;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.rep.observer.Observable;
import fr.rep.observer.Observer;

public class Model implements Observable {
	
	private Score score;
	private ScoreSerializer scoreSerializer;
	private Word mot;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private int nbMots = 0;
	
	public Model(){
		initModel();
	}
	
	private void initModel(){
		score = new Score();
		scoreSerializer = new ScoreSerializer();
		mot = new Word();
	}
	
	public void assign(char c){
		if(this.mot.verifyWord(c) != -1){
			
			if(this.mot.isFinished()){
				this.score.initPoint(this.mot.getNbErreur());
				this.score.setNbMots(this.score.getNbMots()+1);
				JOptionPane.showMessageDialog(null,
						                        "Vous avez trouvé le mot " + this.mot.getMotSecret() +
						                        " en " +
						                        this.mot.getNbCoups() + " coup" + ((this.mot.getNbCoups() > 1) ? "s" : "") +
												", avec " + this.mot.getNbErreur() +
						                        " erreur" + ((this.mot.getNbErreur() > 1) ? "s" : "") + ".\n" +
						                        "\tVous marquez donc " +
						                        this.score.getPointMarque() + " pts.\n" +
												"\tVotre avez maintenant un total de " +
												this.score.getPoints() + " pts.",
												"Résultat",
												JOptionPane.INFORMATION_MESSAGE);
				this.mot.sesame();
				this.mot.setNbErreur(0);
				this.nbMots++;
				
				this.restartObserver();
			}
			
			this.notifyObserver();
		}
		else{
			this.mot.setNbErreur(this.mot.getNbErreur()+1);
			this.notifyObserver();
			if(this.mot.getNbErreur() == 7){
				
				JOptionPane.showMessageDialog(null,
						                        "Le mot était :\n\t" +
						                        this.mot.getMotSecret(),
												"Vous avez perdu", JOptionPane.NO_OPTION);
				
				if(this.scoreSerializer.isTopScore(this.score)){
					String nom = "";
					nom  = (String)JOptionPane.showInputDialog(null,
										"Entrez votre pseudonyme :", "Félicitations",
										JOptionPane.QUESTION_MESSAGE);
					
					this.score.setPseudo(nom);
					score.setNbMots(nbMots);
					this.scoreSerializer.serialize();
					this.scoreObserver();
				}
				else{
					JOptionPane.showMessageDialog(null,
				            "Désolé, vous avez perdu…\n" +
				            "\n\n\tVous n'avez malheureusement pas assez de points pour enregistrer votre score.\n" +
				            "Retentez votre chance à l'occasion !",
				            "Vous avez perdu", JOptionPane.NO_OPTION);
					this.accueilObserver();
				}			
			}
			else{
				this.notifyObserver();
			}
		}		
	}
	
	
	public void assign(char[] letters){
		if(this.mot.verifyWord(letters) != -1){
			
			if(this.mot.isFinished()){
				this.score.initPoint(this.mot.getNbErreur());
				this.score.setNbMots(this.score.getNbMots()+1);
				JOptionPane.showMessageDialog(null,
						                        "Vous avez trouvé le mot " + this.mot.getMotSecret() +
						                        " en " +
						                        this.mot.getNbCoups() + " coup" + ((this.mot.getNbCoups() > 1) ? "s" : "") +
												", avec " + this.mot.getNbErreur() +
						                        " erreur" + ((this.mot.getNbErreur() > 1) ? "s" : "") + ".\n" +
						                        "\tVous marquez donc " +
						                        this.score.getPointMarque() + " pts.\n" +
												"\tVotre avez maintenant un total de " +
												this.score.getPoints() + " pts.",
												"Résultat",
												JOptionPane.INFORMATION_MESSAGE);
				this.mot.sesame();
				this.mot.setNbErreur(0);
				this.nbMots++;
				
				this.restartObserver();
			}
			
			this.notifyObserver();
		}
		else{
			this.mot.setNbErreur(this.mot.getNbErreur()+1);
			this.notifyObserver();
			
			if(this.mot.getNbErreur() == 7){
				score.initPoint(mot.getNbErreur()); // !!!!!!!!!
				JOptionPane.showMessageDialog(null,
						                        "Le mot était :\n\t" +
						                        this.mot.getMotSecret(),
												"Vous avez perdu", JOptionPane.NO_OPTION);
				
				if(this.scoreSerializer.isTopScore(this.score)){
					String nom = "";
					nom  = (String)JOptionPane.showInputDialog(null,
										"Entrez votre pseudonyme :", "Félicitations",
										JOptionPane.QUESTION_MESSAGE);
					
					this.score.setPseudo(nom);
					score.setNbMots(nbMots);
					score.initPoint(mot.getNbErreur());
					this.scoreSerializer.serialize();
					this.scoreObserver();
				}
				else{
					JOptionPane.showMessageDialog(null,
				            "Désolé, vous avez perdu…\n" +
				            "\n\n\tVous n'avez malheureusement pas assez de points pour enregistrer votre score.\n" +
				            "Retentez votre chance à l'occasion !",
				            "Vous avez perdu", JOptionPane.NO_OPTION);
					this.accueilObserver();
				}			
			}
			else{
				this.notifyObserver();
			}
		}	
	}
	
	public void addObserver(Observer obs){
		listObserver.add(obs);
		notifyObserver(); // !!!!!!!!!!!!!
	}
	
	public void removeObserver(){
		listObserver = new ArrayList<Observer>();
	}
	
	public void notifyObserver(){
		for(Observer obs : listObserver)
			obs.update(mot.getMotCache(), score.getPoints(), "image\\pendu" 
		+ mot.getNbErreur() + ".jpg", this.nbMots);
	}
	
	public void restartObserver(){
		for(Observer obs : listObserver)
			obs.restart(mot.getMotCache());
	}
	
	public void accueilObserver(){
		for(Observer obs : listObserver)
			obs.accueil();
	}
	
	public void reset(){
		initModel();
		nbMots = 0;
		mot.setNbErreur(0);
		score = new Score();
	}
	
	public Score[] getScores(){
		return scoreSerializer.getScore();
	}
	
	public void scoreObserver(){
		for(Observer obs : listObserver)
			obs.showScore(this.getScores());
	}
}
