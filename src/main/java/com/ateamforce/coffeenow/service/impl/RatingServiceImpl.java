/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Rating;
import com.ateamforce.coffeenow.model.repository.RatingRepository;
import com.ateamforce.coffeenow.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class RatingServiceImpl implements RatingService {
    
    @Autowired
    RatingRepository ratingRepository;
    
    @Override
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }
    
    @Override
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }
}
