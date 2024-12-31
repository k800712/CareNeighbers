package todoList;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import todoList.Todo;

import java.time.LocalDateTime;

@Component
public class InitDb {

    private final InitService initService;

    public InitDb(InitService initService) {
        this.initService = initService;
    }

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    static class InitService {
        private final EntityManager em;

        InitService(EntityManager em) {
            this.em = em;
        }

        public void dbInit1() {
            Todo todo1 = new Todo("user1", "프로젝트 계획 수립", "새로운 프로젝트의 전체 계획 수립하기", Todo.Priority.HIGH);

            em.persist(todo1);

            Todo todo2 = new Todo("user2", "주간 보고서 작성", "이번 주 업무 진행 상황 정리", Todo.Priority.MEDIUM);

            em.persist(todo2);
        }

        public void dbInit2() {
            Todo todo3 = new Todo("user1", "고객 미팅 준비", "신규 고객과의 미팅 자료 준비", Todo.Priority.HIGH);

            em.persist(todo3);

            Todo todo4 = new Todo("user3", "버그 수정", "애플리케이션의 주요 버그 수정", Todo.Priority.HIGH);

            em.persist(todo4);
        }
    }
}
