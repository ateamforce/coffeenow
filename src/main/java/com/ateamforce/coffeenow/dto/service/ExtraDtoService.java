/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service;

import com.ateamforce.coffeenow.dto.ExtraDto;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtraDtoService {

    void addExtra(ExtraDto extra);

    void deleteExtraById(int extraId);

    void updateExtra(ExtraDto updatedExtra);

    ExtraDto getExtraById(int extraId);

    List<ExtraDto> getAllExtras();

    List<ExtraDto> getRemainigExtrasByExtraCategoryId(int categoryid);

    List<ExtraDto> getAllExtrasByExtraCategoryId(int categoryid);
}
