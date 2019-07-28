/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.Extra;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtraService {

    Extra addExtra(Extra extra);

    void deleteExtraById(int extraId);

    void updateExtra(Extra updatedExtra);

    Extra getExtraById(int extraId);

    List<Extra> getAllExtras();

    List<Extra> getRemainigExtrasByExtraCategoryId(int categoryid);

    public List<Extra> getAllExtrasByExtraCategoryId(int categoryid);

    public void addExtraCategoriesToExtra(Extra extra);
}
