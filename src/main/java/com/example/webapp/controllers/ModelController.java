package com.example.webapp.controllers;

import com.example.webapp.dtos.models.AddModelDto;
import com.example.webapp.services.BrandService;
import com.example.webapp.services.ModelService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/models")
public class ModelController {

    private ModelService modelService;
    private BrandService brandService;

    @Autowired
    public void setModelService(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }


    @ModelAttribute("modelsModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @GetMapping("/all")
    public String getAllModels(Model model) {
        model.addAttribute("models", modelService.allModels());
        model.addAttribute("brands", brandService.allBrand());
        return "showAllModels";
    }
    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("availableBrands", brandService.allBrand());
        return "addModel";
    }
    @PostMapping("/add")
    public String addModel(@Valid AddModelDto addModelDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("modelsModel", addModelDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.modelsModel",
                    result);
            return "redirect:/models/all";
        }
        modelService.addModel(addModelDto);
        return "redirect:/models/all";
    }
}
