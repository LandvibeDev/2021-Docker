package kr.or.connect.service;

import kr.or.connect.dto.DisplayInfoImage;
import kr.or.connect.dto.Product;
import kr.or.connect.dto.ProductImage;
import kr.or.connect.dto.ProductPrice;

import java.util.List;

public interface ProductDetailService {
    public Product getProduct(int displayId);
    public ProductImage getProductImage(int displayId);
    public DisplayInfoImage getDisplayInfoImage(int displayId);
    public int getAVGScore(int displayId);
    public List<ProductPrice> getProductPriceList(int displayId);
}
