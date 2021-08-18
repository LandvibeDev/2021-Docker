package kr.or.connect.service.impl;

import kr.or.connect.dao.ProductDao;
import kr.or.connect.dto.Product;
import kr.or.connect.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {
    @Autowired
    ProductDao productDao;

    @Override
    @Transactional
    public List<Product> getProductList(int categoryId, int start) {
        return productDao.selectProductList(categoryId, start, LIMIT);
    }

    @Override
    @Transactional
    public Integer getTotalCount(int categoryId) {
        return productDao.selectTotalCount(categoryId);
    }
}
