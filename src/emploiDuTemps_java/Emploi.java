package emploiDuTemps_java;

import javax.swing.JFrame;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;

public class Emploi extends JFrame {
	JLabel lbtitre;
	JButton btreq;

	public Emploi() {
		//interface
		this.setTitle("Emploi_du_temps");
		//this.setSize(900,550);
		this.setSize(1000,600);

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		//pn.setBackground(new Color(240,200,200));
		
		pn.setBackground(new Color(173, 216, 230)); // Bleu clair

		
		   
		// Charger les images
		ImageIcon imageGauche = new ImageIcon("LogoISI.PNG");
		ImageIcon imageDroite = new ImageIcon("Logo_UTM.png");

		// Redimensionner les images
		Image imageGaucheResized = imageGauche.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		Image imageDroiteResized = imageDroite.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);

		// Créer de nouveaux ImageIcon à partir des images redimensionnées
		ImageIcon imageGaucheResizedIcon = new ImageIcon(imageGaucheResized);
		ImageIcon imageDroiteResizedIcon = new ImageIcon(imageDroiteResized);

		// Créer des labels pour afficher les images redimensionnées
		JLabel labelGauche = new JLabel(imageGaucheResizedIcon);
		JLabel labelDroite = new JLabel(imageDroiteResizedIcon);

		// Ajuster la position des images
		labelGauche.setBounds(50, 50, 150, 100);
		labelDroite.setBounds(800, 50, 150, 100);

		// Ajouter les labels au panneau
		pn.add(labelGauche);
		pn.add(labelDroite);

		lbtitre=new JLabel("Emploi du temps");
		lbtitre.setBounds(400, 150, 200, 150);

		lbtitre.setFont(new Font("Arial",Font.BOLD,22));
		pn.add(lbtitre);
		
		
		//bouton pour afficher l'interface des requetes
		btreq=new JButton("Cours");
		btreq.setBounds(390,300,200,40);
		btreq.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Cours c=new Cours();
				c.setVisible(true);
			}
		});
		pn.add(btreq);
		
		
		
		//bouton pour afficher l'interface des requetes
		btreq=new JButton("REQUETES");
		btreq.setBounds(390,400,200,40);
		btreq.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Requetes rq=new Requetes();
				rq.setVisible(true);
			}
		});
		pn.add(btreq);
		
		
	}
	public static void main(String[] args) {

		Emploi e=new Emploi();
		e.setVisible(true);

	}
	
}
