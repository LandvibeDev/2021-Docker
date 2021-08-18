package kr.or.connect.service.impl;

import kr.or.connect.dao.CommentDao;
import kr.or.connect.dao.CommentImageDao;
import kr.or.connect.dto.Comment;
import kr.or.connect.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    CommentImageDao commentImageDao;

    @Override
    public List<Comment> getCommentList(int productId, int start) {
        List<Comment> list = commentDao.selectCommentList(productId, start);
        list.forEach(var -> var.setReservationUserCommentImages(commentImageDao.selectCommentImageList(var.getId())));
        return list;
    }

    public int getTotalCount(int productId){
        return commentDao.selectTotalCount(productId);
    }
}
