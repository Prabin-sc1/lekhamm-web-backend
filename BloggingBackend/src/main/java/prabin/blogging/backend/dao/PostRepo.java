package prabin.blogging.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prabin.blogging.backend.model.Category;
import prabin.blogging.backend.model.Post;
import prabin.blogging.backend.model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	  List<Post> findByUser(User user);
	  
	  List<Post> findByCategory(Category category);
	 
}
