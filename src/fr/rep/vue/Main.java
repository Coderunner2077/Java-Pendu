package fr.rep.vue;

import fr.rep.model.Model;
import fr.rep.model.Score;
import fr.rep.observer.Observable;

public class Main {

	public static void main(String[] args) {
		Observable model = new Model();
		Fenetre fen = new Fenetre(model);
	}

}
