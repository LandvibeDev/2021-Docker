package org.edwith.webbe.cardmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public List<BusinessCard> searchBusinessCard(String keyword) {
        List<BusinessCard> cards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql="SELECT * FROM BUSINESS_CARD WHERE NAME LIKE '%"+keyword+"%'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next()){
                BusinessCard card = new BusinessCard();
                card.setName(rs.getString("name"));
                card.setPhone(rs.getString("phone"));
                card.setCompanyName(rs.getString("company_name"));
                card.setCreateDate(rs.getDate("create_date"));
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }


    public int addBusinessCard(BusinessCard businessCard){
        int insertCount = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql="INSERT INTO BUSINESS_CARD VALUES(?,?,?,SYSDATE())";

        try {
            Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, businessCard.getName());
            ps.setString(2, businessCard.getPhone());
            ps.setString(3, businessCard.getCompanyName());

            insertCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return insertCount;
    }
}
