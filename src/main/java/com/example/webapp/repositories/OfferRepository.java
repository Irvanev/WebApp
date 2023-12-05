package com.example.webapp.repositories;

import com.example.webapp.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {
    Optional<Offer> findById(String id);
}
