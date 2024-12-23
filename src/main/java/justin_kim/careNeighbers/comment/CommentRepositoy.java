package justin_kim.careNeighbers.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositoy extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}