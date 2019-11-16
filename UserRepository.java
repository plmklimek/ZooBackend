package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	List<User> user;
	Connection con = null;
	public UserRepository() 
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
	
	public List<User> getUsers() 
	{
		try {
			List <User> users = new ArrayList<>();
			String sql = "select * from pracownicy";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				User a = new User();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setSurname(rs.getString(3));
				a.setPhone(rs.getString(4));
				a.setSex(rs.getString(5));
				a.setSalary(rs.getString(6));
				a.setPosition(rs.getString(7));
				a.setDate(rs.getDate(8));
				
				users.add(a);
			}
			System.out.println(rs);
			return users;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public User getUser(int id)
	{
		try {
			String sql = "select * from pracownicy where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				User a = new User();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setSurname(rs.getString(3));
				a.setPhone(rs.getString(4));
				a.setSex(rs.getString(5));
				a.setSalary(rs.getString(6));
				a.setPosition(rs.getString(7));
				a.setDate(rs.getDate(8));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(User a1)  {
		try {
			String sql = "insert into pracownicy values (?,?,?,?,?,?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			/*
			 * 		a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setSurname(rs.getString(3));
				a.setPhone(rs.getString(4));
				a.setSex(rs.getString(5));
				a.setSalary(rs.getString(6));
				a.setPosition(rs.getString(7));
				a.setDate(rs.getDate(8));
			 */
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setString(3,a1.getSurname());
			st.setString(4,a1.getPhone());
			st.setString(5,a1.getSex());
			st.setString(6,a1.getSalary());
			st.setString(7,a1.getPosition());
			st.setDate(8,a1.getDate());

			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(User a1)  {
		String sql = "update pracownicy set imie=?,nazwisko=?,telefon=?,plec=?,pensja=?,stanowisko=?,data_zatrudnienia=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a1.getName());
			st.setString(2,a1.getSurname());
			st.setString(3,a1.getPhone());
			st.setString(4,a1.getSex());
			st.setString(5,a1.getSalary());
			st.setString(6,a1.getPosition());
			st.setDate(7,a1.getDate());
			st.setInt(8, a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from pracownicy where id=?";
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


