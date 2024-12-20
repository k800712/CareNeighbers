package justin_kim.careNeighbers.noticBoard;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticBoardController {

    private final NoticBoardService noticBoardService;

    public NoticBoardController(NoticBoardService noticBoardService) {
        this.noticBoardService = noticBoardService;
    }
}
