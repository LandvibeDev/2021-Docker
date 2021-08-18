package kr.or.connect.dao;

public class DaoSqls {
    public static final String SELECT_CATEGORIES = "SELECT category.id, category.name, t.count FROM " +
            "(SELECT  p.category_id, count(d.id) AS count FROM display_info AS d JOIN product AS p ON d.product_id=p.id GROUP BY p.category_id)" +
            " AS t JOIN category ON category.id = t.category_id;";

    public static final String SELECT_PROMOTION = "select promotion.id as id, product.id as product_id, category.id as category_id, category.name as category_name, product.description as description, product_image.file_id as file_id " +
            "from promotion, product, category, product_image where category.id = product.category_id and product.id = promotion.product_id and product.id = product_image.product_id and product_image.type = 'ma';";

    public static final String SELECT_PRODUCT_LIST = "select p.id as id, c.id as category_id, d.id as display_info_id, c.name, p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street, d.tel, d.homepage, d.email, d.create_date, d.modify_date, i.file_id " +
            " from category as c, product as p, display_info as d, product_image as i where c.id = p.category_id and p.id = d.product_id and p.id = i.product_id  and i.type = 'ma' " +
            "and if ( :categoryId = '0' , true, c.id = :categoryId ) limit :limit offset :start;";

    public static final String SELECT_TOTAL_COUNT = "select count(*) as total_count from category, product, display_info where category.id = product.category_id and product.id = display_info.product_id and if( :categoryId = '0' , true, :categoryId = category.id);";

    public static final String SELECT_PRODUCT_IMAGE="select p.product_id, p.id as product_image_id, p.type, f.id as file_info_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date from product_image as p, display_info as d, file_info as f where p.product_id = d.product_id and p.file_id = f.id and p.type = 'ma' and d.id = :displayId;";

    public static final String SELECT_DISPLAY_INFO_IMAGE="select d.*, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date from display_info_image as d, file_info as f where d.file_id = f.id  and d.display_info_id = :displayId;";

    public static final String SELECT_PRODUCT_PRICE="select p.* from product_price as p , display_info as d where p.product_id = d.product_id and d.id = :displayId order by p.id desc;";

    public static final String SELECT_PRODUCT="select p.id as id, c.id as category_id, d.id as display_info_id, c.name, p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street, d.tel, d.homepage, d.email, d.create_date, d.modify_date, i.file_id  from category as c, product as p, display_info as d, product_image as i where c.id = p.category_id and p.id = d.product_id and p.id = i.product_id  and i.type = 'ma' and d.id = :displayId;";

    public static final String SELECT_AVG_SCORE="select avg(c.score) as avg_score from reservation_user_comment as c, display_info as d where c.product_id = d.product_id and d.id = :displayId;";

    public static final String SELECT_COMMENT_LIST="SELECT * FROM reservation_user_comment where product_id = :productId order by id desc limit 5 offset :start;";

    public static final String SELECT_COMMENT_IMAGE_LIST="select i.id as comment_image_id, i.reservation_info_id, i.file_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date from reservation_user_comment_image as i, file_info as f where i.file_id = f.id and i.reservation_user_comment_id = :commentId;";
}
