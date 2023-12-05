package com.example.webapp.services;

import com.example.webapp.dtos.offers.AddOfferDto;
import com.example.webapp.dtos.offers.ShowAllOffersDto;
import com.example.webapp.dtos.offers.ShowOfferInfoDto;

import java.util.List;

public interface OfferService {
    public void addOffer(AddOfferDto addOfferDto);
    public List<ShowAllOffersDto> allOffers();
    public void removeOffer(String id);
    public ShowOfferInfoDto showOfferInfo(String id);
}
