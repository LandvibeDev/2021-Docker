package kr.or.connect.dao;

import kr.or.connect.dto.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static kr.or.connect.dao.DaoSqls.*;

@Repository
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

    public CategoryDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> selectCategory(){
        return jdbc.query(SELECT_CATEGORIES, rowMapper);
    }

}
