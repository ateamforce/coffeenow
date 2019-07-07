/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ClientMedia;
import com.ateamforce.coffeenow.model.repository.ClientMediaRepository;
import com.ateamforce.coffeenow.service.ClientMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ClientMediaServiceImplementation implements ClientMediaService {
    
    @Autowired
    ClientMediaRepository clientMediaRepository;

    @Override
    public void addClientMedia(ClientMedia clientMedia) {
        clientMediaRepository.save(clientMedia);
    }

    @Override
    public void deleteClientMedia(ClientMedia clientMedia) {
        clientMediaRepository.delete(clientMedia);
    }
    
}
