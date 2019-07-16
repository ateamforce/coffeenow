/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.repository;

import com.ateamforce.coffeenow.dto.ExtraDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface ExtraDtoRepository extends JpaRepository<ExtraDto,Integer> {

    List<ExtraDto> findAllExtras();

    ExtraDto findExtraById(int extraId);

    @Query(value = "SELECT*from extras e where id not in(select extraid from extrascategories_extras where categoryid=?1)",
            nativeQuery = true)
    public List<ExtraDto> findRemainigExtrasByExtraCategoryId(int categoryid);

    @Query(value = "SELECT*from extras e where id in(select extraid from extrascategories_extras where categoryid=?1)",
            nativeQuery = true)
    public List<ExtraDto> findAllExtrasByExtraCategoryId(int categoryid);
}
