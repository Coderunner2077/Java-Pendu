package fr.rep.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class Word {
	private String motSecret = "", motCache = "";
	private int numero = 0, erreur = 0, nbCoups;
	private char c;
	private char[] letters, charCache;
	public Word(){
		sesame();
	}
	
	public void sesame(){
		motSecret = "";
		Path chemin = Paths.get("file\\dictionnaire.txt");
		try(InputStream input = Files.newInputStream(chemin);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			LineNumberReader lnr = new LineNumberReader(br)){
				do{
					numero = (int)(Math.random()*336530) + 1;
					while(lnr.getLineNumber() != numero)
						motSecret = lnr.readLine();
				}while(motSecret.contains("\\") || motSecret.contains("-"));
				
				motSecret = motSecret.trim().toUpperCase();
				charCache = motSecret.toCharArray();
				for(int i = 0; i < motSecret.length(); i++)
					charCache[i] = '*';
				motCache = new String(charCache);
				System.out.println("sesame : " + motSecret);
			}catch(IOException e){
				JOptionPane.showMessageDialog(null, "Erreur de chargement du dico", "Erreur", 
						JOptionPane.ERROR_MESSAGE);
			}
			nbCoups = 0;
			erreur = 0;
	}
	
	public int verifyWord(char carac){
		boolean bok = false;
		this.c = carac;
		for(int i = 0; i < motSecret.length(); i++){
			if(motSecret.charAt(i) == c){
				charCache[i] = c;
				bok = true;
			}
		}
		++this.nbCoups;
		motCache = new String(charCache);
		return (bok) ? 1 : -1;
	}
	
	public int verifyWord(char[] lets){
		this.letters = lets;
		boolean bok = false;
		for(char car : letters){
			for(int i = 0; i < motSecret.length(); i++){
				if(motSecret.toUpperCase().charAt(i) == car){
					charCache[i] = car;
					bok = true;
				}
			}
		}
		++this.nbCoups;
		motCache = new String(charCache);
		return (bok) ? 1 : -1;
	}
	
	public boolean isFinished(){
		if(motCache.contains("*"))
			return false;
		else
			return true;
	}
	
	public String getMotCache(){
		return motCache;
	}
	
	public String getMotSecret(){
		return motSecret;
	}
	
	public int getNbCoups(){
		return nbCoups;
	}
	
	public int getNbErreur(){
		return erreur;
	}
	
	public void setNbErreur(int err){
		erreur = err;
	}
	
	public void setNbCoups(int coups){
		nbCoups = coups;
	}
}
