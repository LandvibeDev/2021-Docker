package kr.or.connect.dao;

import kr.or.connect.dto.ProductPrice;
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
public class ProductPriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ProductPrice> rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

    public ProductPriceDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProductPrice> selectProductPriceList(int displayId){
        Map<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);

        return jdbc.query(SELECT_PRODUCT_PRICE, params, rowMapper);
    }


}
