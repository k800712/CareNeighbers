package justin_kim.careNeighbers.comment;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepositoy commentRepositoy;

    public CommentService(CommentRepositoy commentRepositoy) {
        this.commentRepositoy = commentRepositoy;
    }
}
