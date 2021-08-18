package kr.or.connect.controller;

import kr.or.connect.dto.Category;
import kr.or.connect.dto.Comment;
import kr.or.connect.dto.Product;
import kr.or.connect.dto.Promotion;
import kr.or.connect.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainApiController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PromotionService promotionService;
    @Autowired
    ProductListService productListService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    CommentService commentService;

    @GetMapping("/categories")
    public Map<String, Object> categories(){
        List<Category> list = categoryService.getCategories();
        int size = list.size();
        Map<String, Object> map = new HashMap<>();
        map.put("size", size);
        map.put("items", list);

        return map;
    }

    @GetMapping("/displayinfos")
    public Map<String, Object> displayinfos(@RequestParam(name="categoryId", required = false, defaultValue = "0")int categoryId,
                                            @RequestParam(name="start", required = false, defaultValue = "0")int start){
        List<Product> list = productListService.getProductList(categoryId, start);
        int productCount = list.size();
        int totalCount = productListService.getTotalCount(categoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("products", list);
        map.put("productCount", productCount);
        map.put("totalCount", totalCount);

        return map;
    }

    @GetMapping("/displayinfos/{displayId}")
    public Map<String, Object> product(@PathVariable(name = "displayId")int displayId){
        Map<String, Object> map = new HashMap<>();
        map.put("product",productDetailService.getProduct(displayId));
        map.put("productImages",productDetailService.getProductImage(displayId));
        map.put("displayInfoImages",productDetailService.getDisplayInfoImage(displayId));
        map.put("avgScore", productDetailService.getAVGScore(displayId));
        map.put("productPrices",productDetailService.getProductPriceList(displayId));

        return map;
    }

    @GetMapping("/promotions")
    public Map<String, Object> promotions(){
        List<Promotion> list = promotionService.getPromotion();
        int size = list.size();
        Map<String, Object> map = new HashMap<>();
        map.put("size", size);
        map.put("items", list);

        return map;
    }

    @GetMapping("/comments")
    public Map<String, Object> comments(@RequestParam(name = "productId", required = false, defaultValue = "0")int productId,
                                        @RequestParam(name = "start", required = false, defaultValue = "0")int start){
        List<Comment> list = commentService.getCommentList(productId, start);
        int commentCount = list.size();

        Map<String, Object> map = new HashMap<>();
        map.put("reservationUserComments", list);
        map.put("commentCount", commentCount);
        map.put("totalCount", commentService.getTotalCount(productId));
        return map;
    }
}
