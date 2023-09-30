package prabin.blogging.backend.services;

import java.util.List;

import prabin.blogging.backend.model.Post;
import prabin.blogging.backend.payloads.PostDto;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer uId, Integer cid);

	// update
	PostDto updatePost(PostDto postDto, Integer id);

	// delete
	void deletPost(Integer id);

	// get
	PostDto getSinglePost(Integer id);

	// get all
	List<PostDto> getAllPost();

	// get all post by category
	List<PostDto> getAllPostByCategory(Integer cid);

	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// search post
	List<PostDto> searchPost(String keyword);

}
