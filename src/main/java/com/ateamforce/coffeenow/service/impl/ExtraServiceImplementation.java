/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.repository.ExtraRepository;
import com.ateamforce.coffeenow.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtraServiceImplementation implements ExtraService{
    
    @Autowired
    ExtraRepository extraRepository;

    @Override
    public void addExtra(Extra extra) {
        extraRepository.save(extra);
    }

    @Override
    public void deleteExtra(Extra extra) {
        extraRepository.delete(extra);
    }
    
}
