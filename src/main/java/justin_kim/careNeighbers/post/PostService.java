package justin_kim.careNeighbers.post;

import justin_kim.careNeighbers.noticBoard.NoticeBoardRepository; // 오타 수정
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import justin_kim.careNeighbers.user.User;
import justin_kim.careNeighbers.user.UserRepository;
import justin_kim.careNeighbers.noticBoard.NoticeBoard;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final NoticeBoardRepository noticeBoardRepository; // 오타 수정

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, NoticeBoardRepository noticeBoardRepository) {
        if (postRepository == null || userRepository == null || noticeBoardRepository == null) {
            throw new IllegalArgumentException("Repositories must not be null");
        }
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.noticeBoardRepository = noticeBoardRepository;
    }

    @Transactional
    public Post createPost(PostRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + request.authorId() + " not found"));

        NoticeBoard noticeBoard = noticeBoardRepository.findById(request.noticeBoardId())
                .orElseThrow(() -> new IllegalArgumentException("NoticeBoard with ID " + request.noticeBoardId() + " not found"));

        Post post = new Post(
                null,
                request.title(),
                request.content(),
                author,
                new Date(),
                0, // 초기 조회수는 0으로 설정
                null,
                noticeBoard
        );

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post with ID " + id + " not found"));
    }

    @Transactional
    public Post updatePost(Long id, PostRequest request) {
        // 게시글 조회
        Post existingPost = getPostById(id);

        // 유효성 검사
        if (request.title() == null || request.title().isBlank()) {
            throw new IllegalArgumentException("Title must not be empty");
        }

        if (request.content() == null || request.content().isBlank()) {
            throw new IllegalArgumentException("Content must not be empty");
        }

        // 게시글 제목 및 내용 업데이트
        existingPost.setTitle(request.title());
        existingPost.setContent(request.content());

        // 변경된 내용을 저장하고 반환
        return postRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post with ID " + id + " does not exist");
        }
        postRepository.deleteById(id);
    }

    public void incrementViews(Long id) {
        Post post = getPostById(id);
        post.incrementViews(); // incrementViews 메서드를 호출하여 조회수 증가
        postRepository.save(post); // 변경된 내용을 저장
    }
}
