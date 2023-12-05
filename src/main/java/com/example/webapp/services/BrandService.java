package com.example.webapp.services;

import com.example.webapp.dtos.brands.AddBrandDto;
import com.example.webapp.dtos.brands.ShowAllBrandsDto;
import com.example.webapp.dtos.brands.ShowBrandInfoDto;
import com.example.webapp.dtos.brands.UpdateBrandDto;

import java.util.List;

public interface BrandService {
    public void addBrand(AddBrandDto addBrandDto);
    public List<ShowAllBrandsDto> allBrand();
    public void removeBrand(String name);
    public ShowBrandInfoDto showBrandInfo(String name);
    public void editBrand(String originalBrandName, AddBrandDto brandDto);
    public AddBrandDto findBrandByName(String brandName);
}
