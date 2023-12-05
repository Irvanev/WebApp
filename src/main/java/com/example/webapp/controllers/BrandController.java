package com.example.webapp.controllers;

import com.example.webapp.dtos.brands.AddBrandDto;
import com.example.webapp.dtos.brands.UpdateBrandDto;
import com.example.webapp.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @ModelAttribute("brandsModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @ModelAttribute("brandsUpdateModel")
    public UpdateBrandDto updateBrand() {
        return new UpdateBrandDto();
    }

    @GetMapping("/all")
    public String getAllBrands(Model model) {
        model.addAttribute("brands", brandService.allBrand());
        return "showAllBrands";
    }

    @GetMapping("/add")
    public String addBrand() {
        return "addBrand";
    }

    @PostMapping("/add")
    public String addBrand(@Valid AddBrandDto addBrandDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("brandsModel", addBrandDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.brandsModel",
                    result);
            return "redirect:/brands/add";
        }
        brandService.addBrand(addBrandDto);
        return "redirect:/brands/all";
    }
    @GetMapping("/brandDelete/{name}")
    public String removeBrand(@PathVariable("name") String name) {
        brandService.removeBrand(name);

        return "redirect:/brands/all";
    }

    @GetMapping("/showBrandInfo/{name}")
    public String showBrandsInfo(@PathVariable("name") String name, Model model) {
        model.addAttribute("brandsInfo", brandService.showBrandInfo(name));

        return "showBrandInfo";
    }
}
