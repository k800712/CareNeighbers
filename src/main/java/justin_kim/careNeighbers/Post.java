package justin_kim.careNeighbers;

import org.apache.catalina.User;

import java.util.Date;
import java.util.List;

public class Post {
    //개별 게시글의 정보 관리
    private Long id;
    private String title;
    private String content;
    private User author;
    private Date createdAt;
    private int views;
    private List<Comment> comments;

    public Post(Long id, String title, String content, User author, Date createdAt, int views, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.views = views;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void incrementViews() {
        // 조회수 증가
    }

    public void addComment(Comment comment) {
        // 댓글 추가
    }

    public void removeComment(Comment comment) {
        // 댓글 삭제
    }

    public void getAllComments() {
        // 모든 댓글 조회

    }
}
