package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PavilonRepository {
	List<Pavilon> pavilon;
	Connection con = null;
	public PavilonRepository() 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "username";
			String password = "password";
			con = DriverManager.getConnection(url,username,password);
			System.out.println(con);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
	
	public List<Pavilon> getPavilons() 
	{
		try {
			List <Pavilon> pavilons = new ArrayList<>();
			String sql = "select * from pawilony";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Pavilon a = new Pavilon();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setDesc(rs.getString(3));
				
				pavilons.add(a);
			}
			System.out.println(rs);
			return pavilons;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Pavilon getPavilon(int id)
	{
		try {
			String sql = "select * from pawilony where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				Pavilon a = new Pavilon();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setDesc(rs.getString(3));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(Pavilon a1)  {
		try {
			String sql = "insert into pawilony values (?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setString(3,a1.getDesc());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(Pavilon a1)  {
		String sql = "update pawilony set nazwa=?, opis=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a1.getName());
			st.setString(2,a1.getDesc());
			st.setInt(3,a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from pawilony where id=?";
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