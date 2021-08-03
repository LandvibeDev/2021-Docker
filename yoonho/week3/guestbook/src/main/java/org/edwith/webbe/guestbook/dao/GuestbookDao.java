package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
    Connection conn = null;
    public GuestbookDao(){
        try{
            conn=DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

        // 코드를 작성하세요.

        String sql = "select * from guestbook order by id";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    Long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String content = rs.getString(3);
                    Date regdate = rs.getDate(4);
                    Guestbook guestbook = new Guestbook(id,name,content,regdate);
                    list.add(guestbook);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
        String sql = "insert into guestbook (name, content, regdate) values (?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,guestbook.getName());
            ps.setString(2,guestbook.getContent());
            ps.setTimestamp(3, new Timestamp(guestbook.getRegdate().getTime()));
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
