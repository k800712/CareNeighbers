package justin_kim.careNeighbers.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank
        @Size(min = 2, max = 100)
        String title,

        @NotBlank
        @Size(min = 2, max = 2000)
        String content,

        Long authorId,

        Long noticeBoardId
) {
}
