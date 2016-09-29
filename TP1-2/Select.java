import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Select{
	public static void main(String args[])
	{
		try{
			Connection con=null;
			Statement stmt;
			Properties prop = new Properties();
			prop.load(new FileInputStream("propriete.txt"));
			
			// enregistrement du driver
			Class.forName(prop.getProperty("driver"));
			
			// connexion à la base
			String url = prop.getProperty("url");
			String nom = prop.getProperty("nom");
			String mdp = prop.getProperty("mdp");
			con = DriverManager.getConnection(url,nom,mdp);
			
			stmt = con.createStatement();
			String query = "select NOM,PRENOM,AGE from CLIENTS";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Liste des clients:");
			
			while (rs.next())
			{
				String n = rs.getString("nom");    // col 1
				String p = rs.getString("prenom"); // col 2
				int a = rs.getInt("age");          // col 3
				System.out.println(n + " " + p + " " + a);
			}
			con.close();
		}catch(Exception e){
			System.out.println("Erreur: "+ e.toString());
		}
	}
}