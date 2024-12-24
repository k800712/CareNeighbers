package justin_kim.careNeighbers.noticBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticeboards")
public class NoticBoardController {

    private final NoticBoardService noticeBoardService;

    @Autowired
    public NoticBoardController(NoticBoardService noticeBoardService) {
        this.noticeBoardService = noticeBoardService;
    }

    @PostMapping
    public NoticeBoard createNoticeBoard(@RequestBody NoticeBoardRequst request) {
        return noticeBoardService.createNoticeBoard(request);
    }

    @GetMapping
    public List<NoticeBoard> getAllNoticeBoards() {
        return noticeBoardService.getAllNoticeBoards();
    }

    @GetMapping("/{id}")
    public NoticeBoard getNoticeBoardById(@PathVariable Long id) {
        return noticeBoardService.getNoticeBoardById(id);
    }

    @PutMapping("/{id}")
    public NoticeBoard updateNoticeBoard(@PathVariable Long id, @RequestBody NoticeBoardRequst request) {
        return noticeBoardService.updateNoticeBoard(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteNoticeBoard(@PathVariable Long id) {
        noticeBoardService.deleteNoticeBoard(id);
    }
}
