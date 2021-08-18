package kr.or.connect.dao;

import kr.or.connect.dto.DisplayInfoImage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static kr.or.connect.dao.DaoSqls.*;

@Repository
public class DisplayInfoImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

    public DisplayInfoImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public DisplayInfoImage selectDisplayInfoImage(int displayId){
        Map<String , Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, rowMapper);
    }

}
