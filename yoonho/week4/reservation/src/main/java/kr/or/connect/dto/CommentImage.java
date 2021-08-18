package kr.or.connect.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentImage {
    private int commentImageId;
    private int reservationInfoId;
    private int fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private int deleteFlag;
    private String createDate;
    private String modifyDate;

    public int getCommentImageId() {
        return commentImageId;
    }

    public void setCommentImageId(int commentImageId) {
        this.commentImageId = commentImageId;
    }

    public int getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(int reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createDate = format.format(createDate);
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.modifyDate = format.format(modifyDate);
    }
}
