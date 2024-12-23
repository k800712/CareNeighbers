package justin_kim.careNeighbers.comment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import justin_kim.careNeighbers.post.Post;
import justin_kim.careNeighbers.user.User;
import justin_kim.careNeighbers.post.PostRepository;
import justin_kim.careNeighbers.user.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepositoy commentRepositoy;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepositoy commentRepositoy, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepositoy = commentRepositoy;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(@Valid CreatCommentRequst request) {
        User author = userRepository.findById(Long.valueOf(request.authorId()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = postRepository.findById(Long.valueOf(request.postId()))
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment parentComment = null;
        if (request.parentCommentId() != null) {
            parentComment = commentRepositoy.findById(Long.parseLong(request.parentCommentId()))
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
        }

        Comment comment = new Comment(
                null,
                request.content(),
                author,
                new Date(),
                post,
                parentComment,
                null
        );
        return commentRepositoy.save(comment);
    }


    public List<Comment> getAllComments() {
        return commentRepositoy.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepositoy.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepositoy.findByPostId(Long.parseLong(postId));
    }

    public Comment updateComment(Long id, @Valid CreatCommentRequst request) {
        Comment existingComment = getCommentById(id);
        existingComment.setContent(request.content());
        return commentRepositoy.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepositoy.deleteById(id);
    }

    public CommentRepositoy getCommentRepositoy() {
        return commentRepositoy;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }
}