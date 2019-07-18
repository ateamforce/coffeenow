/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service.impl;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import com.ateamforce.coffeenow.dto.repository.ExtraCategoryDtoRepository;
import com.ateamforce.coffeenow.dto.service.ExtraCategoryDtoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtraCategoryDtoServiceImpl implements ExtraCategoryDtoService {

    @Autowired
    ExtraCategoryDtoRepository extraCategoryDtoRepository;

    @Override
    public ExtraCategoryDto addExtraCategory(ExtraCategoryDto extraCategory) {
        return extraCategoryDtoRepository.save(extraCategory);
    }

    @Override
    public void deleteExtraCategoryById(int extraCategoryId) {
        extraCategoryDtoRepository.deleteById(extraCategoryId);
    }

    @Override
    public void updateExtraCategory(ExtraCategoryDto updatedExtraCategory) {
        extraCategoryDtoRepository.save(updatedExtraCategory);
    }

    @Override
    public List<ExtraCategoryDto> getAllExtraCategories() {
        return extraCategoryDtoRepository.findAllExtraCategories();
    }

    @Override
    public ExtraCategoryDto getExtraCategoryById(int categoryId) {
        return extraCategoryDtoRepository.findExtraCategoryById(categoryId);
    }

    @Override
    public List<ExtraCategoryDto> getRemainigExtraCategoriesByExtraId(int extraId) {
        return extraCategoryDtoRepository.findRemainigExtraCategoriesByExtraId(extraId);
    }

    @Override
    public List<ExtraCategoryDto> getAllExtraCategoriesByExtraId(int extraId) {
        return extraCategoryDtoRepository.findAllExtraCategoriesByExtraId(extraId);
    }

    @Override
    public List<ExtraCategoryDto> getAllExtraCategoriesByProductCategoryId(int productCategoryId) {
        return extraCategoryDtoRepository.findAllExtraCategoriesByProductCategoryId(productCategoryId);
    }

    @Override
    public List<ExtraCategoryDto> getRemainigExtraCategoriesByProductCategoryId(int productCategoryId) {
        return extraCategoryDtoRepository.findRemainigExtraCategoriesByProductCategoryId(productCategoryId);
    }

}
