package prabin.blogging.backend.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prabin.blogging.backend.model.Category;
import prabin.blogging.backend.model.User;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String title;

	private String content;

	private String image;

	private Date addedDate;
 
	private CategoryDto category;

	private UserDto user;

	
}
