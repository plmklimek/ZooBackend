package com.klimek.first;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository 
{

	List<Alien> aliens;
	Connection con = null;
	public AlienRepository() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/restdb?useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root";
			String password = "123456";
			con = DriverManager.getConnection(url,username,password);
			System.out.println(con);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
	
	public List<Alien> getAliens() 
	{
		try {
			List <Alien> aliens = new ArrayList<>();
			String sql = "select * from alien";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
			System.out.println(rs);
			return aliens;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Alien getAlien(int id)
	{
		try {
			String sql = "select * from alien where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(Alien a1)  {
		try {
			String sql = "insert into alien values (?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setInt(3,a1.getPoints());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(Alien a1)  {
		String sql = "update alien set name=?, points=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from alien where id=?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
}
