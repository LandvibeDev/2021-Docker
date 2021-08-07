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

    DBUtil dbUtil = new DBUtil();

    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

        String sql = "SELECT * from guestbook";

        // try with resource
        // try 안에 사용할 resource를 얻어올 구문을 넣어주면 알아서 닫아줌
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String content = rs.getString("content");
                    Date regdate = rs.getDate("regdate");

                    Guestbook guestbook = new Guestbook(id, name, content, regdate);

                    list.add(guestbook);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return list;
    }

    public void addGuestbook(Guestbook guestbook){

        String sql = "INSERT INTO guestbook (name, content, regdate) VALUES (?, ?, ?)";

        // try with resource
        // try 안에 사용할 resource를 얻어올 구문을 넣어주면 알아서 닫아줌
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, guestbook.getName());
            ps.setString(2, guestbook.getContent());

            Date date = guestbook.getRegdate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlDate);

            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();

        }

        return;

    }
}
