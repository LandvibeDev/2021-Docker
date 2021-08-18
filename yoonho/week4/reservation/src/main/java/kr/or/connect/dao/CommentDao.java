package kr.or.connect.dao;

import kr.or.connect.dto.Comment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.dao.DaoSqls.*;

@Repository
public class CommentDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

    public CommentDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public int selectAvgScore(int displayId){
        Map<String, Integer> params=new HashMap<>();
        params.put("displayId", displayId);

        return jdbc.queryForObject(SELECT_AVG_SCORE, params, (resultSet, i) -> resultSet.getInt("avg_score") );
    }

    public List<Comment> selectCommentList(int productId, int start){
        Map<String, Integer> params=new HashMap<>();
        params.put("productId", productId);
        params.put("start", start);
        return jdbc.query(SELECT_COMMENT_LIST, params, rowMapper);
    }

    public int selectTotalCount(int productId){
        Map<String, Integer> params=new HashMap<>();
        params.put("productId", productId);
        try{
            return jdbc.queryForObject("SELECT count(*) as total_count FROM reservation_user_comment where product_id = :productId group by product_id;", params, (resultSet, i) -> resultSet.getInt("total_count"));
        } catch (DataAccessException e) {
            return 0;
        }
    }
}
