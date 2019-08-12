package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.TokenVerification;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long> {
    
    TokenVerification findByToken(String token);

    TokenVerification findByUser(AppUser user);

    Stream<TokenVerification> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("DELETE FROM TokenVerification t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
    
}