package prabin.blogging.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prabin.blogging.backend.dao.UserRepo;
import prabin.blogging.backend.exceptions.ResourceNotFoundException;
import prabin.blogging.backend.model.User;
import prabin.blogging.backend.payloads.UserDto;
import prabin.blogging.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto u) {
		User user = this.dtoToUser(u);
		User save = this.userRepo.save(user);
		return this.userToDto(save);
	}

	@Override
	public UserDto updateUser(UserDto u, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setAbout(u.getAbout());
		User save = this.userRepo.save(user);
		return this.userToDto(save);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		List<UserDto> collect = allUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User tempUser = this.modelMapper.map(userDto, User.class);
		/*
		 * tempUser.setId(user.getId()); tempUser.setName(user.getName());
		 * tempUser.setEmail(user.getEmail()); tempUser.setPassword(user.getPassword());
		 * tempUser.setAbout(user.getAbout());
		 */
		return tempUser;
	}

	public UserDto userToDto(User u) {
		UserDto temp = this.modelMapper.map(u, UserDto.class);

		/*
		 * temp.setId(u.getId()); temp.setName(u.getName());
		 * temp.setPassword(u.getPassword()); temp.setAbout(u.getAbout());
		 * temp.setEmail(u.getEmail());
		 */
		return temp;
	}

}
