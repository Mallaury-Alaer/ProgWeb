import java.sql.*;
import java.util.*;

public class ProductDAO{
	Connection con = null;
	ArrayList<Product> produitslist = new ArrayList<Products>();

	public ProductDAO(Connection con){
		this.con = con;
	}

	public ArrayList<Product> findAll(){
		Statement stmt = con.createStatement();
		String query = "select * from pruduit;"
		ResultSet rs = stmt.executeQuery(query);
		while(rs.netx()){
			produitslist.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getStriong(4), rs.getInt(5)));		
		}
		return produitslist;
	}

	public Product findById(int ref){
		Statement stmt = con.createStatement();
		String query = "select * from pruduit where id like;"
		ResultSet rs = stmt.executeQuery(query)
	}
}