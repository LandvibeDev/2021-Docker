package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
    private static final String dburl = "jdbc:mysql://localhost:3306/db_schema?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String dbUser = "root";
    private static final String dbpasswd = "7479";


    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql = "SELECT * FROM db_schema.guestbook";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String content = rs.getString("content");
                Date regdate = rs.getDate("regdate");
                Guestbook guestbook = new Guestbook(id,name, content, regdate);
                list.add(guestbook);
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


    public void addGuestbook(Guestbook guestbook){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO db_schema.guestbook (id,name, content,regdate) VALUES (id,?,?,?)";

        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, guestbook.getName());
            ps.setString(2, guestbook.getContent());
            ps.setDate(3, new java.sql.Date(guestbook.getRegdate().getTime()));
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
