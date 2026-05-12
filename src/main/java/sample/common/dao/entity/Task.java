package sample.common.dao.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class Task {

    private Long id;

    private String userName;

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 100, message = "タイトルは100文字以内です")
    private String title;

    @Size(max = 500, message = "内容は500文字以内です")
    private String content;

    @Size(max = 50, message = "担当者名は50文字以内です")
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // startDate
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // endDate
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
 // createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // updatedAt
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}