package justin_kim.careNeighbers.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatCommentRequst(
        @NotBlank
        @Size(min = 2, max = 1000)
        String content,

        @NotBlank
        Long authorId,

        @NotBlank
        Long postId,

        Long parentCommentId) {
}
