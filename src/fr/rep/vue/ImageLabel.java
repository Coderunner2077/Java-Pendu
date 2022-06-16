package fr.rep.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ImageLabel extends JLabel {
	private String imgPath = "image\\pendu0.jpg";
	public ImageLabel(){}
	public ImageLabel(String imgPath){
		this.imgPath = imgPath;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		try{
			Image img = ImageIO.read(new File(imgPath));
			g.drawImage(img, 0, 0, null);
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Erreur de chargement de l'image");
		}
	}
	
	public void setImagePath(String imgPath){
		this.imgPath = imgPath;
		repaint(); // !!!!!!!!!!
	}

}
