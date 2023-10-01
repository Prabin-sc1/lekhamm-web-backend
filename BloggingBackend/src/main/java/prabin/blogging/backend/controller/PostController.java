package prabin.blogging.backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import prabin.blogging.backend.payloads.ApiResponse;
import prabin.blogging.backend.payloads.PostDto;
import prabin.blogging.backend.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ModelMapper mm;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto pd, @PathVariable("userId") Integer a,
			@PathVariable("categoryId") Integer b) {
		PostDto createPost = this.postService.createPost(pd, a, b);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);
		System.out.println(postsByUser.toString());
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer cid) {
//		List<PostDto> postsByUser = this.postService.getPostsByUser(cid);
		List<PostDto> allPostByCategory = this.postService.getAllPostByCategory(cid);
//		System.out.println(postsByUser.toString());
		return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);

	}

	// get all posts
	@GetMapping("/all-posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}

	// get single post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
		PostDto singlePost = this.postService.getSinglePost(postId);
		return new ResponseEntity<PostDto>(singlePost, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletPost(postId);
		return new ApiResponse("Post is successfully deleted", true);
	}

	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedDto = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedDto, HttpStatus.OK);

	}
}
