import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Create{
	public static void main(String args[])
	{
		try{
		Properties prop = new Properties();
		prop.load(new FileInputStream("propriete.txt"));
		
		// enregistrement du driver
		Class.forName(prop.getProperty("driver"));
		
		// connexion à la base
		String url = prop.getProperty("url");
		String nom = prop.getProperty("nom");
		String mdp = prop.getProperty("mdp");
		Connection con = DriverManager.getConnection(url,nom,mdp);
		
		// exécution de la requete
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create table CLIENTS " +
		"(NOM varchar(10), PRENOM varchar(10), AGE int)");
		
		// fermeture des espaces
		con.close();
		}catch(Exception e){
			System.out.println("Erreur: " + e.toString());
		}
	}
}