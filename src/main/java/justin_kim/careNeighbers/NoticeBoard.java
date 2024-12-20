package justin_kim.careNeighbers;

import java.util.Date;
import java.util.List;

public class NoticeBoard {
    //게시판의 기본정보 게시글 목록관리
    //게시글 추가,삭제,조회
    private Long id;
    private String name;
    private String title;
    private String description;
    private Date createdAt;
    private List<Post> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public NoticeBoard(Long id, String name, String title, String description, Date createdAt, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.posts = posts;
    }

    public void addPost(Post post) {
        //게시글 추가

    }

    public void removePost(Post post) {
        //게시글 삭제

    }

    public void getAllPosts() {
        //모든 게시글 조회
    }

    public void getRecentPosts(int count) {
        //최근 게시글 조회
    }
}

