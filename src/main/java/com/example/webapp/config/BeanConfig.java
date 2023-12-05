package com.example.webapp.config;

import com.example.webapp.dtos.offers.AddOfferDto;
import com.example.webapp.dtos.offers.ShowAllOffersDto;
import com.example.webapp.dtos.offers.ShowOfferInfoDto;
import com.example.webapp.dtos.users.AddUserDto;
import com.example.webapp.dtos.users.ShowAllUsersDto;
import com.example.webapp.models.Offer;
import com.example.webapp.models.Users;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Offer.class, ShowAllOffersDto.class)
                .addMapping(Offer::getUsers, ShowAllOffersDto::setUserName);

        modelMapper.typeMap(Offer.class, ShowOfferInfoDto.class)
                .addMapping(Offer::getUsers, ShowOfferInfoDto::setUserName);

        modelMapper.createTypeMap(Users.class, ShowAllUsersDto.class)
                .addMapping(src -> src.getRole().getRoleEnum(), ShowAllUsersDto::setRoleName);


        return modelMapper;
    }
}
