/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service.impl;

import com.ateamforce.coffeenow.dto.ExtraDto;
import com.ateamforce.coffeenow.dto.repository.ExtraDtoRepository;
import com.ateamforce.coffeenow.dto.service.ExtraDtoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtraDtoServiceImpl implements ExtraDtoService {

    @Autowired
    ExtraDtoRepository extraDtoRepository;

    @Override
    public ExtraDto addExtra(ExtraDto extra) {
        return extraDtoRepository.save(extra);
    }

    @Override
    public void deleteExtraById(int extraId) {
        extraDtoRepository.deleteById(extraId);
    }

    @Override
    public void updateExtra(ExtraDto updatedExtra) {
        extraDtoRepository.save(updatedExtra);
    }

    @Override
    public ExtraDto getExtraById(int extraId) {
        return extraDtoRepository.findExtraById(extraId);
    }

    @Override
    public List<ExtraDto> getAllExtras() {
        return extraDtoRepository.findAllExtras();
    }

    @Override
    public List<ExtraDto> getRemainigExtrasByExtraCategoryId(int categoryid) {
        return extraDtoRepository.findRemainigExtrasByExtraCategoryId(categoryid);
    }

    @Override
    public List<ExtraDto> getAllExtrasByExtraCategoryId(int categoryid) {
        return extraDtoRepository.findAllExtrasByExtraCategoryId(categoryid);
    }
}
