package justin_kim.careNeighbers.noticBoard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoticeBoardRequst(
        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @NotBlank
        @Size(min = 2, max = 200)
        String title,

        @Size(max = 1000)
        String description
) {}
