package justin_kim.careNeighbers.noticBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    @Autowired
    public NoticBoardService(NoticeBoardRepository noticeBoardRepository) {
        this.noticeBoardRepository = noticeBoardRepository;
    }

    public NoticeBoard createNoticeBoard(NoticeBoardRequst request) {
        NoticeBoard noticeBoard = new NoticeBoard(
                null,
                request.name(),
                request.title(),
                request.description(),
                new Date(),
                null
        );
        return noticeBoardRepository.save(noticeBoard);
    }

    public List<NoticeBoard> getAllNoticeBoards() {
        return noticeBoardRepository.findAll();
    }

    public NoticeBoard getNoticeBoardById(Long id) {
        return noticeBoardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NoticeBoard not found"));
    }

    public NoticeBoard updateNoticeBoard(Long id, NoticeBoardRequst request) {
        NoticeBoard existingNoticeBoard = getNoticeBoardById(id);
        existingNoticeBoard.setName(request.name());
        existingNoticeBoard.setTitle(request.title());
        existingNoticeBoard.setDescription(request.description());
        return noticeBoardRepository.save(existingNoticeBoard);
    }

    public void deleteNoticeBoard(Long id) {
        noticeBoardRepository.deleteById(id);
    }
}
