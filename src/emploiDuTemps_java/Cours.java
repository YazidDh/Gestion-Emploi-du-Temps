package emploiDuTemps_java;
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

public class Cours extends JFrame {
	Statement st;
	Connexion con=new Connexion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre,lbtitre2,lbmatricule,lbnom,lbcontact,lbclasse,lbmatiere,lbjour,lbheure,lbmatri_ens;
	JTextField tfmatricule,tfnom,tfcontact,tfmatiere,tfmatri_ens;
	JButton btrech,btenrg,btmodif,btsupp,btenrg2,btreq;
	JComboBox comboclasse,combojour,comboheure;
	
	public Cours(){
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

		
		
		

		
		
		
		//----

		//Formulaire d'enregistrement des enseignants
		
		lbtitre=new JLabel("Formulaire d'enregistrement des enseignants");
		lbtitre.setBounds(220,10,400,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		//Matricule
	    lbmatricule=new JLabel("Matricule");
		lbmatricule.setBounds(60,50,170,25);
		lbmatricule.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatricule);
						
		tfmatricule=new JTextField();
		tfmatricule.setBounds(143,50,100,25);
		pn.add(tfmatricule);
		
		//bouton recherche matricule (nom)
		Color c = new Color(94, 99, 249);
		btrech=new ButtonCustom("CHERCHER", c);
		btrech.setBounds(245,50,100,25);
		btrech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String matricule=tfmatricule.getText(),nom=tfnom.getText(),contact=tfcontact.getText();
				//nom like '%$rech%'
		String rq="select * from tb_enseignant where nom like '%"+nom+"%'";
		try{
			st=con.laConnection().createStatement();
			rst=st.executeQuery(rq);
			if(rst.next()){
				
				tfmatricule.setText(rst.getString("matricule"));
				tfnom.setText(rst.getString("nom"));
				tfcontact.setText(rst.getString("contact"));
					
			}
			else{
				JOptionPane.showMessageDialog(null,"Enregistrement inexistant!",null,JOptionPane.ERROR_MESSAGE);
			}	
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }
				
			}
		});
		pn.add(btrech);
		
		
		//nom
		lbnom=new JLabel("Nom");
		lbnom.setBounds(91,80,100,25);
		lbnom.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbnom);
				
		tfnom=new JTextField();
		tfnom.setBounds(143,80,200,25);
		pn.add(tfnom);
		//contact
		lbcontact=new JLabel("Contact");
		lbcontact.setBounds(68,110,100,25);
		lbcontact.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbcontact);
		
		tfcontact=new JTextField();
		tfcontact.setBounds(143,110,200,25);
		pn.add(tfcontact);
		
		//les boutons
		
		//bouton enregistrement enseignant
		Color c2 = new Color(247, 238, 104);
		btenrg=new ButtonCustom("ENREGISTRER", c2);
		btenrg.setBounds(35,160,120,25);
btenrg.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent ev){
		String matricule,nom,contact;
		matricule=tfmatricule.getText();
		nom=tfnom.getText();
		contact=tfcontact.getText();
		String rq1="insert into tb_enseignant(matricule,nom,contact) values('"+matricule+"','"+nom+"','"+contact+"')";
		try{
			st=con.laConnection().createStatement();
			if(!matricule.equals("")&&!nom.equals("")&&!contact.equals("")){
			st.executeUpdate(rq1);
    		JOptionPane.showMessageDialog(null,"Insertion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Matricule existante!",null,JOptionPane.ERROR_MESSAGE);	
	    }
		dispose();
		Cours crs=new Cours();
		crs.setVisible(true);
}});
		pn.add(btenrg);
		
		//bouton modification enseignant(matricule)
		Color c3 = new Color(90, 253, 134);
		btmodif=new ButtonCustom("MODIFIER", c3);
		btmodif.setBounds(170,160,120,25);
		btmodif.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String matricule=tfmatricule.getText(),
						nom=tfnom.getText(),
						contact=tfcontact.getText();
	String rq="update tb_enseignant set nom='"+nom+"',contact='"+contact+"' where matricule='"+matricule+"'";
	try{
		st=con.laConnection().createStatement();
		if(!matricule.equals("")&&!nom.equals("")&&!contact.equals("")){
		st.executeUpdate(rq);
		JOptionPane.showMessageDialog(null,"Modification reussie!",null,JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
		}
	}
	catch(SQLException ex){
    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
    }	
	dispose();
	Cours crs=new Cours();
	crs.setVisible(true);	}
		});
		pn.add(btmodif);
		
//bouton suppression enseignant(matricule)
		Color c4 = new Color(255, 97, 97);
		btsupp=new ButtonCustom("SUPPRIMER", c4);
		btsupp.setBounds(100,200,120,25);
		btsupp.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					String matricule=tfmatricule.getText();
							//nom=tfnom.getText(),
							//contact=tfcontact.getText();
		String rq="delete from tb_enseignant where matricule='"+matricule+"'";
		try{
			st=con.laConnection().createStatement();
			if(JOptionPane.showConfirmDialog(null,"Voulez vous supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
			st.executeUpdate(rq);
    		JOptionPane.showMessageDialog(null,"Suppression reussie!",null,JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }	
		dispose();
		Cours crs=new Cours();
		crs.setVisible(true);	
	}});
		pn.add(btsupp);
		
		
		
		//Formulaire d'enregistrement des séances de cours
		
		lbtitre2=new JLabel("Formulaire d'enregistrement des s�ances de cours");
		lbtitre2.setBounds(200,250,500,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre2);
		
		//Classe
				lbclasse=new JLabel("Classe");
				lbclasse.setBounds(35,290,150,25);
				lbclasse.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbclasse);
				comboclasse=new JComboBox();
				comboclasse.addItem("");
				comboclasse.addItem("LCS1");
				comboclasse.addItem("LIRS1");
				comboclasse.addItem("LSE1");
				comboclasse.addItem("IDL1");
				comboclasse.addItem("ISEOC1");
				comboclasse.addItem("IDISC1");
				comboclasse.addItem("LCS2");
				comboclasse.addItem("LIRS2");
				comboclasse.addItem("LSE2");
				comboclasse.addItem("IDL2");
				comboclasse.addItem("ISEOC2");
				comboclasse.addItem("IDISC2");
				comboclasse.addItem("LCS3");
				comboclasse.addItem("LIRS3");
				comboclasse.addItem("LSE3");
				comboclasse.addItem("IDL3");
				comboclasse.addItem("ISEOC3");
				comboclasse.addItem("IDISC3");
				
				comboclasse.setBounds(120,290,150,25);
				pn.add(comboclasse);
		//matiere
				lbmatiere=new JLabel("Matiere");
				lbmatiere.setBounds(35,320,150,25);
				lbmatiere.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbmatiere);
				tfmatiere=new JTextField();
				tfmatiere.setBounds(120,320,150,25);
				pn.add(tfmatiere);
		//jour
				lbjour=new JLabel("Jour");
				lbjour.setBounds(50,350,150,25);
				lbjour.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbjour);
				
				combojour=new JComboBox();
				combojour.addItem("");
				combojour.addItem("LUNDI");
				combojour.addItem("MARDI");
				combojour.addItem("MERCREDI");
				combojour.addItem("JEUDI");
				combojour.addItem("VENDREDI");
				combojour.addItem("SAMEDI");
				combojour.setBounds(120,350,150,25);
				pn.add(combojour);
				
				//heure
				lbheure=new JLabel("Heure");
				lbheure.setBounds(40,380,150,25);
				lbheure.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbheure);
				
				comboheure=new JComboBox();
				comboheure.addItem("");
				comboheure.addItem("1ere H");
				comboheure.addItem("2eme H");
				comboheure.addItem("3eme H");
				comboheure.addItem("4eme H");
				comboheure.addItem("5eme H");
				comboheure.addItem("6eme H");
				comboheure.addItem("1ere et 2eme H");
				comboheure.addItem("3eme  et 4eme H");
				comboheure.addItem("5eme et 6eme H");
				comboheure.addItem("2eme et 3eme H");
				comboheure.addItem("4eme et 5eme H");
				comboheure.setBounds(120,380,150,25);
				pn.add(comboheure);
				//matricule de l'enseignant
				lbmatri_ens=new JLabel("Matricule enseignant");
				lbmatri_ens.setBounds(10,410,170,25);
				lbmatri_ens.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbmatri_ens);
				
				tfmatri_ens=new JTextField();
				tfmatri_ens.setBounds(190,410,80,25);
				pn.add(tfmatri_ens);
				
		
				//bouton enregistrement seance cours
				
				btenrg2=new ButtonCustom("ENREGISTRER", c2);
				btenrg2.setBounds(120,450,120,25);
				btenrg2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe,matiere,jour,heure,matri_ens;
						classe=comboclasse.getSelectedItem().toString();
						matiere=tfmatiere.getText();
						jour=combojour.getSelectedItem().toString();
						heure=comboheure.getSelectedItem().toString();
						matri_ens=tfmatri_ens.getText();
			String rq1="insert into tb_cours(classe,matiere,jour,heure,matricule_ens) values('"+classe+"','"+matiere+"','"+jour+"','"+heure+"','"+matri_ens+"')";
						try{
							st=con.laConnection().createStatement();
							if(!matiere.equals("")&&!classe.equals("")&&!jour.equals("")&&!heure.equals("")){
							st.executeUpdate(rq1);
				    		JOptionPane.showMessageDialog(null,"Insertion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
							}
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
					    }
						String rq2="update tb_cours set num_jour=1 where jour='LUNDI'";
						String rq3="update tb_cours set num_jour=2 where jour='MARDI'";
						String rq4="update tb_cours set num_jour=3 where jour='MERCREDI'";
						String rq5="update tb_cours set num_jour=4 where jour='JEUDI'";
						String rq6="update tb_cours set num_jour=5 where jour='VENDREDI'";
						String rq7="update tb_cours set num_jour=6 where jour='SAMEDI'";
						try{
							st=con.laConnection().createStatement();
							st.executeUpdate(rq2);
							st.executeUpdate(rq3);
							st.executeUpdate(rq4);
							st.executeUpdate(rq5);
							st.executeUpdate(rq6);
							st.executeUpdate(rq7);
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
					    }
						dispose();
						Cours crs=new Cours();
						crs.setVisible(true);
					}
					
				});
				pn.add(btenrg2);
				
				//bouton pour afficher l'interface des requetes
				Color c5 = new Color(106, 144, 145);
				btreq=new ButtonCustom("REQUETES", c5);
				btreq.setBounds(120,485,120,25);
				btreq.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						Requetes rq=new Requetes();
						rq.setVisible(true);
					}
				});
				pn.add(btreq);
				
				///liste enseignant
				 DefaultTableModel df=new  DefaultTableModel();
				  init();
				  pn.add(scroll);
				 df.addColumn("Matricule");
				 df.addColumn("Nom");
				 df.addColumn("Contact");
				 table.setModel(df);
				 table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
				 table.getTableHeader().setOpaque(false);
				 table.getTableHeader().setBackground(new Color(32, 136, 203));
				 table.getTableHeader().setForeground(new Color(255, 255, 255));
				 table.setRowHeight(25);

				 String rq="select * from tb_enseignant order by nom";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq);
					 while(rst.next()){
						 df.addRow(new Object[]{
		rst.getString("matricule"),rst.getString("nom"),rst.getString("contact")
								 });
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 ///liste séance cours
				 DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("Classe");
				 df2.addColumn("Matiere");
				 df2.addColumn("Jour");
				 df2.addColumn("Heure");
				 df2.addColumn("Enseignant");
				 table2.setModel(df2);
				 table2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
				 table2.getTableHeader().setOpaque(false);
				 table2.getTableHeader().setBackground(new Color(32, 136, 203));
				 table2.getTableHeader().setForeground(new Color(255, 255, 255));
				 table2.setRowHeight(25);
				 String rq2="select * from tb_cours order by id desc";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
		rst.getString("matricule_ens")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
		
	}
	private void init(){
		table=new JTable();
		scroll=new JScrollPane();
		scroll.setBounds(400,40,460,200);
		scroll.setViewportView(table);
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(320,300,540,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {

		Cours cr=new Cours();
		cr.setVisible(true);

	}

}
