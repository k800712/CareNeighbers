package justin_kim.careNeighbers;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {  //댓글 정보를 관리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parentComment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replies;


    public Comment(Long id, String content, User author,
                   Date createdAt, Post post, Comment parentComment,
                   List<Comment> replies) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.post = post;
        this.parentComment = parentComment;
        this.replies = replies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public void addReply(Comment reply) {
        // 대댓글 추가
    }

    public void removeReply(Comment reply) {
        // 대댓글 삭제
    }

    public void getAllReplies() {
        // 모든 대댓글 조회
    }
}

