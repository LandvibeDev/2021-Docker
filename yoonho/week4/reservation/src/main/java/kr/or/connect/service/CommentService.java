package kr.or.connect.service;

import kr.or.connect.dto.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentList(int productId, int start);
    public int getTotalCount(int productId);
}
