/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtrascategoryExtra;
import com.ateamforce.coffeenow.model.repository.ExtrascategoryExtraRepository;
import com.ateamforce.coffeenow.service.ExtrascategoryExtraService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtrascategoryExtraServiceImpl implements ExtrascategoryExtraService {

    @Autowired
    ExtrascategoryExtraRepository extrascategoryExtraRepository;

    @Override
    public void addAllExtrascategoryExtra(List<ExtrascategoryExtra> extrascategoryExtralist) {
        extrascategoryExtraRepository.saveAll(extrascategoryExtralist);
    }

    @Override
    public List<ExtrascategoryExtra> getByExtraid(int extraId) {
        return extrascategoryExtraRepository.findByExtraid(extraId);
    }

    @Override
    public List<ExtrascategoryExtra> getByExtraCategoryid(int extraCategoryId) {
        return extrascategoryExtraRepository.findByExtraCategoryid(extraCategoryId);
    }

    @Override
    public void deleteAllGivenExtrascategoryExtras(List<ExtrascategoryExtra> extrascategoryExtra) {
        extrascategoryExtraRepository.deleteAll(extrascategoryExtra);
    }

}
