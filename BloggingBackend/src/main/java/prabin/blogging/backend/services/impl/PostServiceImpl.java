package prabin.blogging.backend.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prabin.blogging.backend.dao.CategoryRepo;
import prabin.blogging.backend.dao.PostRepo;
import prabin.blogging.backend.dao.UserRepo;
import prabin.blogging.backend.exceptions.ResourceNotFoundException;
import prabin.blogging.backend.model.Category;
import prabin.blogging.backend.model.Post;
import prabin.blogging.backend.model.User;
import prabin.blogging.backend.payloads.PostDto;
import prabin.blogging.backend.services.PostService;

// why service annotation so that it becomes component of spring

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer uId, Integer cid) {
		User user = this.userRepo.findById(uId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", uId));
		Category category = this.categoryRepo.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", cid));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImage("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post p = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "post id", id));
		p.setTitle(postDto.getTitle());
		p.setContent(postDto.getContent());
		Post updatedPost = this.postRepo.save(p);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletPost(Integer id) {
		Post p = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post ", "post id ", id));
		this.postRepo.delete(p);
	}

	@Override
	public PostDto getSinglePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "postid", id));
		PostDto map = this.modelMapper.map(post, PostDto.class);
		return map;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = this.postRepo.findAll();
		List<PostDto> collect = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer cid) {
		Category cat = this.categoryRepo.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", cid));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> tempDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return tempDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User tempUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
//		List<Post> posts = tempUser.getPosts();
		List<Post> posts = this.postRepo.findByUser(tempUser);
		List<PostDto> collect = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		return null;
	}

}
