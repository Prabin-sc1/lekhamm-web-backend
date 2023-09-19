package prabin.blogging.backend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotBlank
	@Size(min = 4, message = "Username must be of 4 characters.")
	private String name;
	@Email
	private String email;
	@NotNull
	@Size(min=8,message = "Password should be of 8 characters.")
	private String password;
	@NotNull
	private String about;

}
