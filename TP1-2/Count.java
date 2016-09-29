import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Count{
	public static void main(String args[]){
		try{
			Statement stmt;
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("propriete.txt"));
			
			// enregistrement du driver
			Class.forName(prop.getProperty("driver"));
			
			// connexion à la base
			String url = prop.getProperty("url");
			String nom = prop.getProperty("nom");
			String mdp = prop.getProperty("mdp");
			Connection con = DriverManager.getConnection(url,nom,mdp);
			stmt = con.createStatement();
			
			String query = "select count(*) as nblignes FROM clients";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int nb=rs.getInt("nblignes");
			
			System.out.println("Nombre de lignes:" + nb);
		
		}catch(Exception e){
			System.out.println("Erreur: "+e.toString());
		}
	}
}