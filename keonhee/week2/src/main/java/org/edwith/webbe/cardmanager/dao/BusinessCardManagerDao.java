package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
    private static String dburl = "jdbc:mysql://localhost:3306/cardmanager";
    private static String dbUser = "root";
    private static String dbpasswd = "boost";

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<BusinessCard> searchBusinessCard(String keyword) {
        List<BusinessCard> cards = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql = "SELECT * FROM card WHERE name like '%" + keyword + "%'";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String companyName = rs.getString("companyName");
                Date createDate = rs.getDate("createDate");

                BusinessCard card = new BusinessCard(name, phone, companyName);
                card.setCreateDate(createDate);
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return cards;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO card (name, phone, companyName, createDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, businessCard.getName());
            ps.setString(2, businessCard.getPhone());
            ps.setString(3, businessCard.getCompanyName());
            ps.setDate(4, new java.sql.Date(businessCard.getCreateDate().getTime()));

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return businessCard;
    }
}
