package justin_kim.careNeighbers.noticBoard;


import org.springframework.stereotype.Service;

@Service
public class NoticBoardService {

    private final NoticeBoardRepositoy noticeBoardRepositoy;


    public NoticBoardService(NoticeBoardRepositoy noticeBoardRepositoy) {
        this.noticeBoardRepositoy = noticeBoardRepositoy;
    }

}
