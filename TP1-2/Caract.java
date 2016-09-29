import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Caract{
	public static void main(String args[]){
		try{
			Connection con=null;
			String query = "select * from Clients";
			Properties prop = new Properties();
			prop.load(new FileInputStream("propriete.txt"));
			
			// enregistrement du driver
			Class.forName(prop.getProperty("driver"));
			
			// connexion à la base
			String url = prop.getProperty("url");
			String nom = prop.getProperty("nom");
			String mdp = prop.getProperty("mdp");
			con = DriverManager.getConnection(url,nom,mdp);
				
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int nbCols = rsmd.getColumnCount();
			System.out.println("Cette table contient "+ nbCols + " colonnes");
			
			for (int i = 1; i <= nbCols; i++)
			{
				System.out.print("Colonne "+ i);
				System.out.print(" | Nom  : " + rsmd.getColumnName(i));
				System.out.print(" | Type : " +  rsmd.getColumnTypeName(i)+"\n");

			}
			// dans tous les cas fermer la connexion
			con.close();
		}catch(Exception e){
			System.out.println("Erreur : "+e.toString());
		}
	}

}