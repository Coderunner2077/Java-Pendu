package fr.rep.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;



public class ScoreSerializer {
	private File file;
	
	private Score[] scoreList = new Score[10];
	
	private boolean isTop = false;
	public ScoreSerializer(){
		file = new File("file\\top_score.txt");
		if(file.exists()){
			try(FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis)){
					scoreList = (Score[])ois.readObject();
				}catch(IOException e){
					JOptionPane.showMessageDialog(null, "Erreur de chargement du fichier-score", "Erreur", 
							JOptionPane.ERROR_MESSAGE);
				}
				catch(ClassNotFoundException e){
					JOptionPane.showMessageDialog(null, "Erreur de chargement du fichier-score", "Erreur", 
							JOptionPane.ERROR_MESSAGE);
				}
		}
		else{
			for(int i = 0; i < 10; i++){
				scoreList[i] = new Score(); // !!!!!!!
			}
		}
			
	}
	public Score[] getScore(){
		return scoreList;
	}
	
	
	
	public boolean isTopScore(Score score){
		isTop = false;
		for(int i = 0; i < scoreList.length; i++){
			Score scr = new Score();
			scr = scoreList[i];
			System.out.println("Score : " + scr.toString());
			
			if(score.getPoints() > scr.getPoints() && score.getPoints() > 0){
				isTop = true;
				scoreList[i] = score;
				score = scr;
			}
		}		
		return isTop;
	}
	
	public void serialize(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
										new BufferedOutputStream(
												new FileOutputStream(
														new File("file/top_score.txt"))));
			oos.writeObject(scoreList);
			oos.close();
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier des scores !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier des scores !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}

