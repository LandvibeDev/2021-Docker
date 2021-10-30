package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
	private static String dbUser = "connectuser";
	private static String dbpassword = "connect123!@#";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> list = new ArrayList<>();
    
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	String sql = "SELECT name,phone,companyName FROM BusinessCard WHERE name like ?";
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpassword);
    			PreparedStatement ps = conn.prepareStatement(sql)){

			ps.setString(1, "%"+keyword+"%");		
    		try(ResultSet rs = ps.executeQuery()){

    			while(rs.next()) {
    				String name = rs.getString(1);
    				String phone = rs.getString(2);
    				String companyName = rs.getString(3);
    				
					BusinessCard businessCard = new BusinessCard(name,phone,companyName);
					list.add(businessCard);
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return list;
    }	

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	int insertCount = 0;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} //Driver Loading
    	String sql = "INSERT INTO BusinessCard (name,phone,companyName) VALUES(?,?,?)";
    	try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpassword);
    			PreparedStatement ps = conn.prepareStatement(sql)){
    			ps.setString(1, businessCard.getName());
    			ps.setString(2, businessCard.getPhone());
    			ps.setString(3, businessCard.getCompanyName());
    			
    			insertCount = ps.executeUpdate();
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return businessCard;
    }
}
