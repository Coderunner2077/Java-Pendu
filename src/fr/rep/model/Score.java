package fr.rep.model;

import java.io.Serializable;

public class Score implements Serializable{
	private String info;
	private String pseudo;
	private int points, pointMarque;
	private int nbMots;
	
	public Score(){
		pseudo = ".......";
		points = 0;
		nbMots = 0;
	}
	public Score(String pseudo, int pts, int nbMots){
		this.pseudo = pseudo;
		this.points = pts;
		this.nbMots = nbMots;
	}
	
	public void initPoint(int nbErreur){
		switch(nbErreur){
			case 0 :
				points += 100;
				pointMarque = 100;
				break;
			case 1 : 
				points += 50;
				pointMarque = 50;
				break;
			case 2 :
				points += 35;
				pointMarque = 35;
				break;
			case 3 : 
				points += 25;
				pointMarque = 25;
				break;
			case 4 :
				points += 15;
				pointMarque = 15;
				break;
			case 5 :
				points += 10;
				pointMarque = 10;
				break;
			case 6 :
				points += 5;
				pointMarque = 5;
				break;
			default :
				points += 0;
			
		}
	}
	
	public String getPseudo(){
		return pseudo;
	}
	
	public int getPoints(){
		return points;
	}
	
	public int getNbMots(){
		return nbMots;
	}
	
	public void setPoints(int pts){
		points = pts;
	}
	public void setPseudo(String pseudo){
		this.pseudo = (pseudo == null) ? "........" : pseudo;
	}
	
	public void setNbMots(int nbMots){
		this.nbMots = nbMots;
	}
	
	public int getPointMarque(){
		return pointMarque;
	}
	
	public String toString(){
		info = this.pseudo + " -> " + this.points + " Pts " + "(" + this.nbMots + " mots)\n";
		return info;
	}
}