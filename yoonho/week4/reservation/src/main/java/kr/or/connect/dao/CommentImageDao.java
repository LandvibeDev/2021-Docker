package kr.or.connect.dao;

import kr.or.connect.dto.Comment;
import kr.or.connect.dto.CommentImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.dao.DaoSqls.*;

@Repository
public class CommentImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

    public CommentImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<CommentImage> selectCommentImageList(int commentId){
        Map<String ,Integer> params = new HashMap<>();
        params.put("commentId", commentId);
        return jdbc.query(SELECT_COMMENT_IMAGE_LIST,params,rowMapper);
    }
}
