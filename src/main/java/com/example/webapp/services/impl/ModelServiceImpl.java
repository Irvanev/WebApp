package com.example.webapp.services.impl;

import com.example.webapp.dtos.brands.ShowBrandInfoDto;
import com.example.webapp.dtos.models.AddModelDto;
import com.example.webapp.dtos.models.ShowAllModelsDto;
import com.example.webapp.dtos.models.ShowModelInfoDto;
import com.example.webapp.models.Model;
import com.example.webapp.repositories.BrandRepository;
import com.example.webapp.repositories.ModelRepository;
import com.example.webapp.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    public void addModel(AddModelDto addModelDto) {
        addModelDto.setCreated(LocalDateTime.now());
        addModelDto.setModified(LocalDateTime.now());
        Model model = modelMapper.map(addModelDto, Model.class);
        model.setBrand(brandRepository.findByName(addModelDto.getBrandName()).orElse(null));

        modelRepository.saveAndFlush(model);
    }
    public List<ShowAllModelsDto> allModels() {
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ShowAllModelsDto.class))
                .collect(Collectors.toList());
    }

    public ShowModelInfoDto showModelInfo(String name) {
        return modelMapper.map(modelRepository.findByName(name), ShowModelInfoDto.class);
    }

    public void removeModel(String name) {
        modelRepository.deleteByName(name);
    }
}
