package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sakel
 */
@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

    Store findById(@Param("id") int id);
    
    @Modifying
    @Query(value = "UPDATE appusers SET enabled = ?1 WHERE id = ?2", nativeQuery = true)
    void enableStoreById(int enabled, int storeId);
    
}
