package prabin.blogging.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import prabin.blogging.backend.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
}
