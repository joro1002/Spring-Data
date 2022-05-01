package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory() throws IOException {
        if (this.categoryRepository.count() !=0){
            return;
        }
        String[] fileContent = fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
        for (String s : fileContent) {
            Category category = new Category(s);
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return this.categoryRepository.getOne(categoryId);
    }
}
