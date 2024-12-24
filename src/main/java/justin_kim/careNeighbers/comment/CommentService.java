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

    private final CommentRepositoy commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepositoy commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(@Valid CreatCommentRequst request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment parentComment = null;
        if (request.parentCommentId() != null) {
            parentComment = commentRepository.findById(request.parentCommentId())
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
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment updateComment(Long id, @Valid CreatCommentRequst request) {
        Comment existingComment = getCommentById(id);
        existingComment.setContent(request.content());
        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
