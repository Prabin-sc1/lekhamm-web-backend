package prabin.blogging.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import prabin.blogging.backend.payloads.ApiResponse;
import prabin.blogging.backend.payloads.CategoryDto;
import prabin.blogging.backend.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// create
	@RequestMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto c) {
		CategoryDto createCategory = this.categoryService.createCategory(c);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/{cid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto c, @PathVariable("cid") int id) {
		CategoryDto updateCategory = this.categoryService.updateCategory(c, id);
		return ResponseEntity.ok(updateCategory);

	}

	// delete
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("cid") int id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!", true), HttpStatus.OK);
	}

	// get
	@GetMapping("/{cid}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("cid") int id) {
		CategoryDto category = this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);

	}

	// get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAll() {
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}

}
