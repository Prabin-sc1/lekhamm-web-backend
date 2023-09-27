package prabin.blogging.backend.services;

import java.util.List;

import prabin.blogging.backend.model.Post;
import prabin.blogging.backend.payloads.PostDto;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer uId, Integer cid);

	// update
	Post updatePost(PostDto postDto, Integer id);

	// delete
	void deletPost(Integer id);

	// get
	Post getSinglePost(Integer id);

	// get all
	List<Post> getAllPost();

	// get all post by category
	List<PostDto> getAllPostByCategory(Integer cid);

	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// search post
	List<Post> searchPost(String keyword);

}
