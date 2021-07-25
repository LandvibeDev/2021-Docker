package org.edwith.webbe.cardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

public class BusinessCardManagerDao {

	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String dbUser = "connectionuser";
	private static String dbPassword = "connect123!@#";

	public List<BusinessCard> searchBusinessCard(String keyword) {
		// 구현하세요.
		List<BusinessCard> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver"); // load mysql driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

		String sql = "SELECT name, phone_num, company, create_date from namecards where name like ?";

		// try with resource
		// try 안에 사용할 resource를 얻어올 구문을 넣어주면 알아서 닫아줌
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, "%" + keyword + "%");

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String name = rs.getString(1);
					String phone_num = rs.getString(2);
					String company = rs.getString(3);
					Date create_date = rs.getDate(4);

					BusinessCard card = new BusinessCard(name, phone_num, company);
					card.setCreateDate(create_date);
					list.add(card);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return list;

	}

	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		int insertCount = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver"); // load mysql driver
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "INSERT INTO namecards (name, phone_num, company, create_date) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, businessCard.getName());
			ps.setString(2, businessCard.getPhone());
			ps.setString(3, businessCard.getCompanyName());

			Date date = businessCard.getCreateDate();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ps.setDate(4, sqlDate);

			insertCount = ps.executeUpdate();
			// INSERT, UPDATE, DELETE는 executeUpdate()로 query 실행

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
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
