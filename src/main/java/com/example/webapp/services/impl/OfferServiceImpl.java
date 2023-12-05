package com.example.webapp.services.impl;

import com.example.webapp.dtos.offers.AddOfferDto;
import com.example.webapp.dtos.offers.ShowAllOffersDto;
import com.example.webapp.dtos.offers.ShowOfferInfoDto;
import com.example.webapp.dtos.users.ShowUserInfoDto;
import com.example.webapp.models.Offer;
import com.example.webapp.repositories.ModelRepository;
import com.example.webapp.repositories.OfferRepository;
import com.example.webapp.repositories.UserRepository;
import com.example.webapp.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(ModelRepository modelRepository, OfferRepository offerRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void addOffer(AddOfferDto addOfferDto) {
        addOfferDto.setCreated(LocalDateTime.now());
        addOfferDto.setModified(LocalDateTime.now());
        Offer offer = modelMapper.map(addOfferDto, Offer.class);
        offer.setModel(modelRepository.findByName(addOfferDto.getModelName()).orElse(null));
        offer.setUsers(userRepository.findByUserName(addOfferDto.getUserName()).orElse(null));

        offerRepository.saveAndFlush(offer);
    }
    public List<ShowAllOffersDto> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowAllOffersDto.class))
                .collect(Collectors.toList());
    }

    public ShowOfferInfoDto showOfferInfo(String id) {
        return modelMapper.map(offerRepository.findById(id), ShowOfferInfoDto.class);
    }

    public void removeOffer(String id) {
        offerRepository.deleteById(id);
    }
}
