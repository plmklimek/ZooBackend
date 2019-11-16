package com.klimek.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
	List<Animal> animal;
	Connection con = null;
	public AnimalRepository() 
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
	
	public List<Animal> getAnimals() 
	{
		try {
			List <Animal> animals = new ArrayList<>();
			String sql = "select * from zwierzeta";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Animal a = new Animal();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setSex(rs.getString(3));
				a.setGroup(rs.getString(4));
				a.setDate(rs.getDate(5));
				a.setWybiegi(rs.getInt(6));
				
				animals.add(a);
			}
			System.out.println(rs);
			return animals;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Animal getAnimal(int id)
	{
		try {
			String sql = "select * from zwierzeta where id="+id;
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				Animal a = new Animal();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setSex(rs.getString(3));
				a.setGroup(rs.getString(4));
				a.setDate(rs.getDate(5));
				a.setWybiegi(rs.getInt(6));
				return a;	
			}
			return null;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void create(Animal a1)  {
		try {
			String sql = "insert into zwierzeta values (?,?,?,?,?,?)";
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setString(3,a1.getSex());
			st.setString(4,a1.getGroup());
			st.setDate(5,a1.getDate());
			st.setInt(6,a1.getWybiegi());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void update(Animal a1)  {
		String sql = "update zwierzeta set nazwa=?, plec=?, gromada=?, data_urodzenia=?, wybiegi_id=? where id = ?";
		try {
			// TODO Auto-generated method stub
			java.sql.PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,a1.getName());
			st.setString(2,a1.getSex());
			st.setString(3,a1.getGroup());
			st.setDate(4,a1.getDate());
			st.setInt(5,a1.getWybiegi());
			st.setInt(6,a1.getId());
			st.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from zwierzeta where id=?";
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


