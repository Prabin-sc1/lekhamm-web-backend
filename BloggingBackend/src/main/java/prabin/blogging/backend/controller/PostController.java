package prabin.blogging.backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
