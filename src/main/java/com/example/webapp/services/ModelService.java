package com.example.webapp.services;

import com.example.webapp.dtos.brands.ShowBrandInfoDto;
import com.example.webapp.dtos.models.AddModelDto;
import com.example.webapp.dtos.models.ShowAllModelsDto;
import com.example.webapp.dtos.models.ShowModelInfoDto;

import java.util.List;

public interface ModelService {
    public void addModel(AddModelDto addModelDto);
    public List<ShowAllModelsDto> allModels();
    public void removeModel(String name);
    public ShowModelInfoDto showModelInfo(String id);
}
