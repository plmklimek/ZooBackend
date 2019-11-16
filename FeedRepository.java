package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedRepository {
	List<Feed> Feed;
	Connection con = null;
	public FeedRepository() 
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
	
	public List<Feed> getFeeds() 
	{
		try {
			List <Feed> feeds = new ArrayList<>();
			String sql = "select * from karmienie";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Feed a = new Feed();
				a.setId(rs.getInt(1));
				a.setDate(rs.getDate(2));
				a.setIlosc(rs.getInt(3));
				a.setZwierzeta_id(rs.getInt(4));
				a.setPracownicy_id(rs.getInt(5));
				a.setPokarm_id(rs.getInt(6));
				
				feeds.add(a);
			}
			System.out.println(rs);
			return feeds;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Feed getFeed(int id)
	{
		try {
			String sql = "select * from karmienie where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				Feed a = new Feed();
				a.setId(rs.getInt(1));
				a.setDate(rs.getDate(2));
				a.setIlosc(rs.getInt(3));
				a.setZwierzeta_id(rs.getInt(4));
				a.setPracownicy_id(rs.getInt(5));
				a.setPokarm_id(rs.getInt(6));
				
			
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(Feed a1)  {
		try {
			String sql = "insert into karmienie values (?,?,?,?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,a1.getId());
			st.setDate(2,a1.getDate());
			st.setInt(3,a1.getIlosc());
			st.setInt(4,a1.getZwierzeta_id());
			st.setInt(5,a1.getPracownicy_id());
			st.setInt(6,a1.getPokarm_id());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(Feed a1)  {
		String sql = "update karmienie set data=?, ilosc=?, zwierzeta_id=?, pracownicy_id=?, pokarm_id=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setDate(1,a1.getDate());
			st.setInt(2,a1.getIlosc());
			st.setInt(3,a1.getZwierzeta_id());
			st.setInt(4,a1.getPracownicy_id());
			st.setInt(5,a1.getPokarm_id());
			st.setInt(6,a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from karmienie where id=?";
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


