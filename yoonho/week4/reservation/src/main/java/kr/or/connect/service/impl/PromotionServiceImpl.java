package kr.or.connect.service.impl;

import kr.or.connect.dao.PromotionDao;
import kr.or.connect.dto.Promotion;
import kr.or.connect.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionDao promotionDao;

    @Override
    @Transactional
    public List<Promotion> getPromotion() {
        return promotionDao.selectPromotion();
    }
}