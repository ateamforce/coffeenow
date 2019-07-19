/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.repository.ExtraRepository;
import com.ateamforce.coffeenow.service.ExtraService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtraServiceImpl implements ExtraService {

    @Autowired
    ExtraRepository extraRepository;

    @Override
    public Extra addExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    @Override
    public void deleteExtraById(int extraId) {
        extraRepository.deleteById(extraId);
    }

    @Override
    public void updateExtra(Extra updatedExtra) {
        extraRepository.save(updatedExtra);
    }

    @Override
    public Extra getExtraById(int extraId) {
        return extraRepository.findExtraById(extraId);
    }

    @Override
    public List<Extra> getAllExtras() {
        return extraRepository.findAllExtras();
    }

    @Override
    public List<Extra> getRemainigExtrasByExtraCategoryId(int categoryid) {
        return extraRepository.findRemainigExtrasByExtraCategoryId(categoryid);
    }

}
