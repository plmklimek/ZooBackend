package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
	List<Food> food;
	Connection con = null;
	public FoodRepository() 
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
	
	public List<Food> getFoods() 
	{
		try {
			List <Food> foods = new ArrayList<>();
			String sql = "select * from pokarm";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Food a = new Food();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setIlosc(rs.getInt(3));
				
				foods.add(a);
			}
			System.out.println(rs);
			return foods;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Food getFood(int id)
	{
		try {
			String sql = "select * from pokarm where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				Food a = new Food();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setIlosc(rs.getInt(3));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(Food a1)  {
		try {
			String sql = "insert into pokarm values (?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setInt(3,a1.getIlosc());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(Food a1)  {
		String sql = "update pokarm set nazwa=?, ilosc=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a1.getName());
			st.setInt(2,a1.getIlosc());
			st.setInt(3,a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from pokarm where id=?";
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

