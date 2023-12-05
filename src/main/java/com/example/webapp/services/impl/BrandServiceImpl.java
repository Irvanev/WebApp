package com.example.webapp.services.impl;

import com.example.webapp.dtos.brands.AddBrandDto;
import com.example.webapp.dtos.brands.ShowAllBrandsDto;
import com.example.webapp.dtos.brands.ShowBrandInfoDto;
import com.example.webapp.dtos.brands.UpdateBrandDto;
import com.example.webapp.models.Brand;
import com.example.webapp.repositories.BrandRepository;
import com.example.webapp.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    public void addBrand(AddBrandDto addBrandDto) {
        addBrandDto.setCreated(LocalDateTime.now());
        addBrandDto.setModified(LocalDateTime.now());
        brandRepository.saveAndFlush(modelMapper.map(addBrandDto, Brand.class));
    }

    public List<ShowAllBrandsDto> allBrand() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, ShowAllBrandsDto.class))
                .collect(Collectors.toList());
    }

    public ShowBrandInfoDto showBrandInfo(String name) {
        return modelMapper.map(brandRepository.findByName(name), ShowBrandInfoDto.class);
    }

    public AddBrandDto findBrandByName(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> modelMapper.map(brand, AddBrandDto.class))
                .orElse(null);
    }


    public void removeBrand(String name) {
        brandRepository.deleteByName(name);
    }

    public void editBrand(String originalBrandName, AddBrandDto brandDto) {
        Optional<Brand> existingBrandOptional = brandRepository.findByName(originalBrandName);

        if (existingBrandOptional.isPresent()) {
            Brand existingBrand = existingBrandOptional.get();
            existingBrand.setName(brandDto.getName());
            existingBrand.setModified(LocalDateTime.now());

            brandRepository.saveAndFlush(existingBrand);
        } else {
            throw new NoSuchElementException("Brand not found for update: " + originalBrandName);
        }
    }

}
