package justin_kim.careNeighbers.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "사용자 이름 또는 이메일은 비어 있을 수 없습니다.") String usernameOrEmail,
        @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.") String password
) {}
