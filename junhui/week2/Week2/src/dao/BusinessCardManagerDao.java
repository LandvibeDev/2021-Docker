package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.BusinessCard;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/card";
	private static String dbUser = "root";
	private static String dbpasswd = "7479";

	public List<BusinessCard> searchBusinessCard(String keyword) {
		List<BusinessCard> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT name, phone, companyName, createDate FROM card WHERE name like '%?%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);

			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String companyName = rs.getString("companyName");
				Date createDate = rs.getDate("createDate");
				BusinessCard bc = new BusinessCard(name, phone, companyName);
				bc.setCreateDate(createDate);
				list.add(bc);
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

		return list;

	}

	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO card (name, phone,companyName, createDate) VALUES ( ?, ?, ?, ? )";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());
			ps.setDate(4, (java.sql.Date) businessCard.getCreateDate());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return businessCard;
	}
}
