package justin_kim.careNeighbers.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoy extends JpaRepository<Comment, Long> {
}
