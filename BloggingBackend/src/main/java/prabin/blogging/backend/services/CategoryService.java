package prabin.blogging.backend.services;

import java.util.List;

import prabin.blogging.backend.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto c);

	CategoryDto getCategory(Integer id);

	List<CategoryDto> getAllCategory();

	CategoryDto updateCategory(CategoryDto c, Integer id);

	void deleteCategory(Integer id);

}
