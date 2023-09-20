package prabin.blogging.backend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer cid;

	@NotBlank
	@Size(min = 3, max = 20, message = "Title should be of atleast 3 character and not more than 20")
	private String categoryName;

	@NotBlank
	@Size(min = 5, max = 200, message = "Title should be of atleast 5 character and not more than 200")
	private String categoryDescription;
}
