package justin_kim.careNeighbers.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatCommentRequst(
        @NotBlank
        @Size(min = 2, max = 1000)
        String content,

        @NotBlank
        String authorId,

        @NotBlank
        String postId,

        String parentCommentId){



}



