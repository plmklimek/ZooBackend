package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatWalksRepository {
	List<CatWalks> catwalk;
	Connection con = null;
	public CatWalksRepository() 
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
	
	public List<CatWalks> getCatWalks() 
	{
		try {
			List <CatWalks> catwalks = new ArrayList<>();
			String sql = "select * from wybiegi";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				CatWalks a = new CatWalks();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setType(rs.getString(3));
				a.setPavilonN(rs.getInt(4));

				
				catwalks.add(a);
			}
			System.out.println(rs);
			return catwalks;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public CatWalks getCatWalk(int id)
	{
		try {
			String sql = "select * from wybiegi where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				CatWalks a = new CatWalks();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setType(rs.getString(3));
				a.setPavilonN(rs.getInt(4));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(CatWalks a1)  {
		try {
			String sql = "insert into wybiegi values (?,?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setString(3,a1.getType());
			st.setInt(4,a1.getPavilonN());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(CatWalks a1)  {
		String sql = "update wybiegi set nazwa=?, typ=?, pawilony_id=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a1.getName());
			st.setString(2,a1.getType());
			st.setInt(3,a1.getPavilonN());
			st.setInt(4,a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from wybiegi where id=?";
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


