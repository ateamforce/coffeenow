/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.ExtrascategoryExtra;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtrascategoryExtraService {

    public void addAllExtrascategoryExtra(List<ExtrascategoryExtra> extrascategoryExtra);

    List<ExtrascategoryExtra> getByExtraCategoryid(int extraCategoryId);

    public void deleteAllGivenExtrascategoryExtras(List<ExtrascategoryExtra> extrascategoryExtra);
}
