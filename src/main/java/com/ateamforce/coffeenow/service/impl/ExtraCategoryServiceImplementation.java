/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.repository.ExtraCategoryRepository;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtraCategoryServiceImplementation implements ExtraCategoryService {
    
    @Autowired
    ExtraCategoryRepository extraCategoryRepository;

    @Override
    public void addExtraCategory(ExtraCategory extraCategory) {
        extraCategoryRepository.save(extraCategory);
    }

    @Override
    public void deleteExtraCategory(ExtraCategory extraCategory) {
        extraCategoryRepository.delete(extraCategory);
    }
    
}
