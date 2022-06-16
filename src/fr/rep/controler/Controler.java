package fr.rep.controler;

import fr.rep.model.Model;
import fr.rep.observer.Observable;

public class Controler {
	
	private Model model;
	
	public Controler(Observable mod){
		model = (Model)mod;
	}
	
	public void control(char c){
	
		switch(c){
			case 'A':
				char[] cs = {'A', 'À', 'Â'};
				model.assign(cs);
				break;
			case 'E' :
				char[] cs1 = {'E', 'É', 'Ê', 'È', 'Ë'};
				model.assign(cs1);
				break;
			case 'I' :
				char[] cs2 = {'I', 'Î', 'Ï'};
				model.assign(cs2);
				break;
			case 'O' :
				char[] cs3 = {'O', 'Ô', 'Ö'};
				model.assign(cs3);
				break;
			case 'U' :
				char[] cs4 = {'U', 'Û', 'Ü'};
				model.assign(cs4);
				break;
			case 'C' :
				char[] cs5 = {'C', 'Ç'};
				model.assign(cs5);
				break;
			default :
				model.assign(c);
				break;
		}
		
	}	
}
