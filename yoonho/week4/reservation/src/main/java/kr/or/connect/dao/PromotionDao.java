package kr.or.connect.dao;

import kr.or.connect.dto.Promotion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static kr.or.connect.dao.DaoSqls.*;

@Repository
public class PromotionDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

    public PromotionDao(DataSource dataSource) { this.jdbc = new NamedParameterJdbcTemplate(dataSource); }

    public List<Promotion> selectPromotion() { return jdbc.query(SELECT_PROMOTION,rowMapper); }

}
