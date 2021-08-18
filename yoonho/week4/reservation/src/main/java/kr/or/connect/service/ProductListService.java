package kr.or.connect.service;

import kr.or.connect.dto.Product;

import java.util.List;

public interface ProductListService {
    public static final int LIMIT = 4;
    public List<Product> getProductList(int categoryId, int start);
    public Integer getTotalCount(int categoryId);

}
