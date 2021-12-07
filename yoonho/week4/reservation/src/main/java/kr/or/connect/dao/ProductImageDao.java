package kr.or.connect.dao;

import kr.or.connect.dto.ProductImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static kr.or.connect.dao.DaoSqls.*;


@Repository
public class ProductImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

    public ProductImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public ProductImage selectProductImage(int displayId){
        Map<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.queryForObject(SELECT_PRODUCT_IMAGE, params, rowMapper);
    }
}
