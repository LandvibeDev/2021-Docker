package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
        List<BusinessCard> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select name, phone, company_name, create_date from business_card where name like ?";
        try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,"%"+keyword+"%");
            try (ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    String name = rs.getString(1);
                    String phone = rs.getString(2);
                    String company = rs.getString(3);
                    BusinessCard card = new BusinessCard(name,phone,company);
                    card.setCreateDate(rs.getTimestamp(4));
                    list.add(card);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into business_card (name,phone,company_name, create_date) values (?,?,?,?)";

        try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,businessCard.getName());
            ps.setString(2,businessCard.getPhone());
            ps.setString(3,businessCard.getCompanyName());
            ps.setTimestamp(4,new Timestamp(businessCard.getCreateDate().getTime()));

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return businessCard;
    }
}
