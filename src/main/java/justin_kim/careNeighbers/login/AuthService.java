package justin_kim.careNeighbers.login;

import justin_kim.careNeighbers.user.User;
import justin_kim.careNeighbers.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // PasswordEncoder 주입
    }

    public User login(String usernameOrEmail, String password) {
        User user = findUserByUsernameOrEmail(usernameOrEmail); // 사용자 검색 메서드 호출

        if (!passwordEncoder.matches(password, user.password())) {
            throw new IllegalArgumentException("로그인 정보가 올바르지 않습니다.");
        }

        return user; // 인증된 사용자 반환
    }

    // 사용자 이름 또는 이메일로 사용자 찾기
    private User findUserByUsernameOrEmail(String usernameOrEmail) {
        return (User) userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 이름 또는 이메일입니다."));
    }
}
