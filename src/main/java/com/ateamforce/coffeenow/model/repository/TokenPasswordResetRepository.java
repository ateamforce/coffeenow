package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.TokenPasswordReset;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenPasswordResetRepository extends JpaRepository<TokenPasswordReset, Long> {
    
    TokenPasswordReset findByToken(String token);

    TokenPasswordReset findByUser(AppUser user);

    Stream<TokenPasswordReset> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("DELETE FROM TokenPasswordReset t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
    
}