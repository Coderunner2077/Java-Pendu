package fr.rep.observer;

import java.awt.Image;

import fr.rep.model.Score;

public interface Observer { // !!!!!!!!!!!
	public void update(String mot, int pts, String imgPath, int nbMots); // !!!pts!!!!imgPath!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void restart(String word);
	public void accueil();
	public void showScore(Score[] list);
}
