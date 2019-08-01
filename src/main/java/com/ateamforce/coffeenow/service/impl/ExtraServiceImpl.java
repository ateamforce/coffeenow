/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ExtrascategoryExtra;
import com.ateamforce.coffeenow.model.repository.ExtraRepository;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.ExtrascategoryExtraService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class ExtraServiceImpl implements ExtraService {

    private final static Logger LOGGER = Logger.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    ExtraRepository extraRepository;

    @Autowired
    ExtrascategoryExtraService extrascategoryExtraService;

    @Autowired
    ImageHandlerService imageHandlerService;

    @Autowired
    Environment env;

    @Override
    public Extra addExtra(Extra extra) {
        boolean hasChanged = false;

        Extra persistedExtra = extraRepository.save(extra);

        if (!extra.getImage().isEmpty()) {
            persistedExtra.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.extras.items"),
                            persistedExtra.getId(),
                            extra
                    )
            );
            hasChanged = true;
        }

        if (extra.getExtracategoriesList() != null) {
            addExtraCategoriesToExtra(extra);
        }

        return (hasChanged) ? extraRepository.save(persistedExtra) : persistedExtra;
    }

    @Override
    public void deleteExtraById(int extraId) {
        boolean hasImage = getExtraById(extraId).isHasimage();
        extraRepository.deleteById(extraId);

        if ((getExtraById(extraId)) == null && hasImage) {
            imageHandlerService.deleteImage(env
                    .getProperty("front.images.extras.items"), extraId);
        }
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

    @Override
    public List<Extra> getAllExtrasByExtraCategoryId(int categoryid) {
        return extraRepository.findAllExtrasByExtraCategoryId(categoryid);
    }

    @Override
    public void addExtraCategoriesToExtra(Extra extra) {
        int extraId = extra.getId();
        try {
            extrascategoryExtraService
                    .deleteAllGivenExtrascategoryExtras(extrascategoryExtraService
                            .getByExtraid(extraId));
        } catch (NullPointerException e) {
            LOGGER.error("No Extras Found");
        } finally {
            List<ExtraCategory> extraCategoriesList = extra.getExtracategoriesList();
            List<ExtrascategoryExtra> extrascategoryExtraList = new ArrayList();
            for (ExtraCategory extraCategory : extraCategoriesList) {
                extrascategoryExtraList.add(new ExtrascategoryExtra(extraCategory.getId(), extraId));
            }
            extrascategoryExtraService
                    .addAllExtrascategoryExtra(extrascategoryExtraList);
        }
    }

}
