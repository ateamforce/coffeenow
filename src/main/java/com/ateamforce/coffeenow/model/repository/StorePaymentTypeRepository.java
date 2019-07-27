package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.StorePaymenttype;
import com.ateamforce.coffeenow.model.StorePaymenttypePK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sakel
 */
@Repository
public interface StorePaymentTypeRepository extends JpaRepository<StorePaymenttype, StorePaymenttypePK> {
    
    List<StorePaymenttype> findByStoreid(@Param("storeid") int storeid);
    
}
