package kr.or.connect.dao;

import kr.or.connect.dto.Product;
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
public class ProductDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Product> selectProductList(int categoryId, int start, int limit){
        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("start", start);
        params.put("limit", limit);
        return jdbc.query(SELECT_PRODUCT_LIST, params, rowMapper);
    }

    public Integer selectTotalCount(int categoryId){
        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);
        return jdbc.queryForObject(SELECT_TOTAL_COUNT, params, (resultSet, i) -> resultSet.getInt("total_count"));
    }

    public Product selectProduct(int displayId){
        Map<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.queryForObject(SELECT_PRODUCT, params, rowMapper);
    }
}
