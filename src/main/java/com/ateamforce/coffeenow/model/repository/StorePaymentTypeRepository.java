package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.StorePaymenttype;
import com.ateamforce.coffeenow.model.StorePaymenttypePK;
import java.util.List;
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
public interface StorePaymentTypeRepository extends JpaRepository<StorePaymenttype, StorePaymenttypePK> {
    
    List<StorePaymenttype> findByStoreid(@Param("storeid") int storeid);
    
    @Modifying
    @Query(value = "DELETE FROM stores_paymenttypes WHERE storeid = ?1", nativeQuery = true)
    void deleteAllByStoreId(int storeId);
    
    @Modifying
    @Query(value = "INSERT INTO stores_paymenttypes (storeid, paymenttypeid) VALUES (?1, ?2)", nativeQuery = true)
    void addOne(int storeId, int paymentTypeId);
    
}
