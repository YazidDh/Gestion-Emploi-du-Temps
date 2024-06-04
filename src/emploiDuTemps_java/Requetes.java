package emploiDuTemps_java;

 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class Requetes extends JFrame {
	Statement st;
	Connexion con=new Connexion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbclasse,lbmatiere,lbtitre,lbtitre2,lbid,lbclasse2;
	JTextField tfmatiere,tfid;
	JComboBox comboclasse,comboclasse2;
	JButton btrech,btsupp,btrech2;
	
	public Requetes(){
		//interface
		this.setTitle("Requetes");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//container
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(173, 216, 230)); // Bleu clair
		
//Les sÃ©ances de cours dans la semaine d'une matiÃ¨re dans une classe
		
		lbtitre=new JLabel("Les séances de cours dans la semaine d'une matière dans une classe");
		lbtitre.setBounds(20,5,800,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		//classe
		lbclasse=new JLabel("Classe");
		lbclasse.setBounds(30,40,150,25);
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
		comboclasse.setBounds(30,70,150,25);
		pn.add(comboclasse);
		
		
		
		//matiere
				lbmatiere=new JLabel("Matière");
				lbmatiere.setBounds(200,40,150,25);
				lbmatiere.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbmatiere);
				
				tfmatiere=new JTextField();
				tfmatiere.setBounds(200,70,150,25);
				pn.add(tfmatiere);
				
				
		//bouton recherche
				Color c = new Color(94, 99, 249);
				btrech=new ButtonCustom("CHERCHER", c);
				btrech.setBounds(360,70,120,25);
				btrech.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe=comboclasse.getSelectedItem().toString(),
								matiere=tfmatiere.getText();
						DefaultTableModel df2=new  DefaultTableModel();
						  init2();
						  pn.add(scroll2);
						 df2.addColumn("ID");
						 df2.addColumn("Classe");
						 df2.addColumn("Matiere");
						 df2.addColumn("Jour");
						 df2.addColumn("Heure");
						 df2.addColumn("Nom enseignant");
						 df2.addColumn("Contact enseignant");
						 table2.setModel(df2);
						 table2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
						 table2.getTableHeader().setOpaque(false);
						 table2.getTableHeader().setBackground(new Color(32, 136, 203));
						 table2.getTableHeader().setForeground(new Color(255, 255, 255));
						 table2.setRowHeight(25);
						 String rq2="select * from enseignant_cours where classe='"+classe+"' and matiere='"+matiere+"' order by num_jour";
						 try{
							 st=con.laConnection().createStatement();
							 rst=st.executeQuery(rq2);
							 while(rst.next()){
								 df2.addRow(new Object[]{
				rst.getString("id"),rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
				rst.getString("nom"),rst.getString("contact")
										 });
								 
							   } 
							 }
							 
						 catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
						    }
						
					}
				});
				pn.add(btrech);				
				

				//id
				lbid=new JLabel("ID");
				lbid.setBounds(20,340,150,25);
				lbid.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbid);
				
				tfid=new JTextField();
				tfid.setBounds(50,340,90,25);
				pn.add(tfid);
			//bouton supprimer
				Color c4 = new Color(255, 97, 97);
				btsupp=new ButtonCustom("SUPPRIMER", c4);
				btsupp.setBounds(150,340,110,25);
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String id=tfid.getText();
						String rq="delete from tb_cours where id='"+id+"'";
						try{
							st=con.laConnection().createStatement();
		if(JOptionPane.showConfirmDialog(null,"voulez vous Supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							 st.executeUpdate(rq);
							 JOptionPane.showMessageDialog(null,"Supprï¿½ssion ï¿½ffectuï¿½e avec succï¿½s !",null,JOptionPane.INFORMATION_MESSAGE);
		}
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
					    }
						
					}
					
				});
				pn.add(btsupp);
				
				
				
				//Emploi du temps de la semaine par classe
				lbtitre2=new JLabel("Emploi du temps de la semaine par classe");
				lbtitre2.setBounds(20,380,800,30);
				lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
				pn.add(lbtitre2);
				
		
		//classe2
				lbclasse2=new JLabel("Classe");
				lbclasse2.setBounds(30,420,150,25);
				lbclasse2.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbclasse2);
				
				comboclasse2=new JComboBox();
				comboclasse2.addItem("");
				comboclasse2.addItem("LCS1");
				comboclasse2.addItem("LIRS1");
				comboclasse2.addItem("LSE1");
				comboclasse2.addItem("IDL1");
				comboclasse2.addItem("ISEOC1");
				comboclasse2.addItem("IDISC1");
				comboclasse2.addItem("LCS2");
				comboclasse2.addItem("LIRS2");
				comboclasse2.addItem("LSE2");
				comboclasse2.addItem("IDL2");
				comboclasse2.addItem("ISEOC2");
				comboclasse2.addItem("IDISC2");
				comboclasse2.addItem("LCS3");
				comboclasse2.addItem("LIRS3");
				comboclasse2.addItem("LSE3");
				comboclasse2.addItem("IDL3");
				comboclasse2.addItem("ISEOC3");
				comboclasse2.addItem("IDISC3");
				comboclasse2.setBounds(100,420,150,25);
				pn.add(comboclasse2);
		
		
		//btrech2
		//bouton recherche
				
				btrech2=new ButtonCustom("CHERCHER", c);
				btrech2.setBounds(260,420,120,25);
				btrech2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe=comboclasse2.getSelectedItem().toString();
						DefaultTableModel df2=new  DefaultTableModel();
						  init2();
						  pn.add(scroll2);
						 df2.addColumn("ID");
						 df2.addColumn("Classe");
						 df2.addColumn("Jour");
						 df2.addColumn("Matiere");
						 df2.addColumn("Heure");
						 df2.addColumn("Nom enseignant");
						 df2.addColumn("Contact enseignant");
						 table2.setModel(df2);
						 table2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
						 table2.getTableHeader().setOpaque(false);
						 table2.getTableHeader().setBackground(new Color(32, 136, 203));
						 table2.getTableHeader().setForeground(new Color(255, 255, 255));
						 table2.setRowHeight(25);
						 String rq2="select * from enseignant_cours where classe='"+classe+"' order by num_jour,heure";
						 try{
							 st=con.laConnection().createStatement();
							 rst=st.executeQuery(rq2);
							 while(rst.next()){
								 df2.addRow(new Object[]{
				rst.getString("id"),rst.getString("classe"),rst.getString("jour"),rst.getString("matiere"),rst.getString("heure"),
				rst.getString("nom"),rst.getString("contact")
										 });
								 
							   } 
							 }
							 
						 catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
						    }
						
					}
				});
				pn.add(btrech2);
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,120,770,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
	Requetes rq=new Requetes();
	rq.setVisible(true);
	}

}
