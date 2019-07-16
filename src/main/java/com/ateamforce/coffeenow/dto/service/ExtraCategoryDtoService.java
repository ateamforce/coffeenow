/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtraCategoryDtoService {

    void addExtraCategory(ExtraCategoryDto extraCategory);

    void deleteExtraCategoryById(int extraCategoryId);

    void updateExtraCategory(ExtraCategoryDto updatedExtraCategory);

    List<ExtraCategoryDto> getAllExtraCategories();

    ExtraCategoryDto getExtraCategoryById(int categoryId);

    List<ExtraCategoryDto> getRemainigExtraCategoriesByExtraId(int extraId);

    List<ExtraCategoryDto> getAllExtraCategoriesByExtraId(int extraId);
}
