import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Insert
	{
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
			
			// execution de la requête
			for(int i=1; i<=50; i++)
			{
				Statement stmt = con.createStatement();
				stmt.executeUpdate("insert into CLIENTS values('nom_"+i+"', 'prenom_"+i+"',"+i+")" );
			}
			// fermeture des espaces
			con.close();
		}catch(Exception e){
			System.out.println("Erreur: "+e.toString());
		}
	}
}