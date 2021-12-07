package kr.or.connect.service.impl;

import kr.or.connect.dao.*;
import kr.or.connect.dto.DisplayInfoImage;
import kr.or.connect.dto.Product;
import kr.or.connect.dto.ProductImage;
import kr.or.connect.dto.ProductPrice;
import kr.or.connect.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductImageDao productImageDao;
    @Autowired
    DisplayInfoImageDao displayInfoImageDao;
    @Autowired
    ProductPriceDao productPriceDao;
    @Autowired
    CommentDao commentDao;

    @Override
    @Transactional
    public Product getProduct(int displayId) {
        return productDao.selectProduct(displayId);
    }

    @Override
    @Transactional
    public ProductImage getProductImage(int displayId) {
        return productImageDao.selectProductImage(displayId);
    }

    @Override
    public DisplayInfoImage getDisplayInfoImage(int displayId) {
        return displayInfoImageDao.selectDisplayInfoImage(displayId);
    }

    @Override
    public int getAVGScore(int displayId) {
        return commentDao.selectAvgScore(displayId);
    }

    @Override
    @Transactional
    public List<ProductPrice> getProductPriceList(int displayId) {
        return productPriceDao.selectProductPriceList(displayId);
    }
}
