package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
	private static String driver = "com.mysql.jdbc.Driver";	
	private static String dburl = "jdbc:mysql://localhost:3306/projectdb?useSSL=false&serverTimezone=Asia/Seoul";
	private static String dbUser = "projectuser";
	private static String dbpasswd = "project123!@#";
	
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();
        try {
			Class.forName(driver);	//드라이버 불러옴
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT * FROM guestbook";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					long id = rs.getLong("id"); //하나씩 꺼냄
					String name = rs.getString("name");
					String content = rs.getString("content");
					Date regdate = rs.getDate("regdate");
					Guestbook guestbook = new Guestbook(id, name, content, regdate);
					guestbook.setRegdate(regdate);
					list.add(guestbook); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
        // 코드를 작성하세요.
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
    	try {
			Class.forName("com.mysql.jdbc.Driver");	//드라이버 불러옴
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO guestbook (name, content, regdate) VALUES ( ?, ?, ? )"; //query문.

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd); //try with resource.
				PreparedStatement ps = conn.prepareStatement(sql)) { //conn객체로 ps객체 얻어옴.

//			ps.setLong(1, guestbook.getId()); //'?'에 대한 값을 바인딩.
			ps.setString(1, guestbook.getName()); //'?'에 대한 값을 바인딩.
			ps.setString(2,  guestbook.getContent());
			ps.setDate(3, new java.sql.Date( guestbook.getRegdate().getTime()));

            ps.executeUpdate();
//			insertCount = ps.executeUpdate(); //select는 excuteQeury() / insert, update, delete는 excuteUpadate().

		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		return guestbook; //query가 실행되면 받아오는 값을 return.
    }
}
