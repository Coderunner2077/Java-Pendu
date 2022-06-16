package fr.rep.observer;

import java.awt.event.ActionListener;

import fr.rep.model.Score;

public interface Observable {// !!!!!!!!!!!!
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver();
	public void restartObserver();
	public void scoreObserver();
	public void accueilObserver();
	public Score[] getScores(); //!!!!!!!!!
	public void reset();
}
