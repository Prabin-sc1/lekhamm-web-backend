package prabin.blogging.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prabin.blogging.backend.dao.CategoryRepo;
import prabin.blogging.backend.exceptions.ResourceNotFoundException;
import prabin.blogging.backend.model.Category;
import prabin.blogging.backend.payloads.CategoryDto;
import prabin.blogging.backend.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto c) {
		Category map = this.modelMapper.map(c, Category.class);
		Category save = this.categoryRepo.save(map);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category c1 = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", id));
		return this.modelMapper.map(c1, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categoryList = this.categoryRepo.findAll();
		List<CategoryDto> collect = categoryList.stream().map(c -> this.modelMapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto c, Integer id) {
		Category category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", id));
		category.setCategoryName(c.getCategoryName());
		category.setCategoryDescription(c.getCategoryDescription());
		Category c1 = this.categoryRepo.save(category);
		return this.modelMapper.map(c1, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category c = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", id));
		this.categoryRepo.delete(c);

	}

}
