import java.sql.*;
import java.util.Properties;
import java.io.*;

public class Lister{
	public static void main(String args[]){
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
			Statement stmt;
			
			stmt = con.createStatement();
			String query = "select * from "+args[0];
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Contenu de la table: "+args[0]);
			ResultSetMetaData rsmd = rs.getMetaData();
			int nbCols = rsmd.getColumnCount();
			
			while(rs.next()){
				String cont="";
				for(int i =1; i<=nbCols; i++){
					cont = cont+rs.getString(rsmd.getColumnName(i))+"\t"; 
				}
				System.out.println(cont);
			}
			con.close();
			
		}catch(Exception e){
			System.out.println("Erreur: "+e.toString());
		}
	}
}