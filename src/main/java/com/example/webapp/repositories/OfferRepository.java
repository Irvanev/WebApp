package com.example.webapp.repositories;

import com.example.webapp.dtos.offers.ShowAllOffersDto;
import com.example.webapp.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {
    Optional<Offer> findById(String id);

    @Query("SELECT o FROM Offer o WHERE o.users.userName = :userName")
    List<Offer> findByUserName(@Param("userName") String userName);
}