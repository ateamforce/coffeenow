package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.AppUserToken;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserTokenRepository extends JpaRepository<AppUserToken, Long> {
    
    AppUserToken findByToken(String token);

    AppUserToken findByUser(AppUser user);

    Stream<AppUserToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("DELETE FROM AppUserToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
    
}