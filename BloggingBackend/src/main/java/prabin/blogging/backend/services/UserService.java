package prabin.blogging.backend.services;

import java.util.List;

import prabin.blogging.backend.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto u);
	UserDto updateUser(UserDto u, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
	

}
