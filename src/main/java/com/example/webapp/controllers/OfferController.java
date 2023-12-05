package com.example.webapp.controllers;

import com.example.webapp.dtos.offers.AddOfferDto;
import com.example.webapp.services.ModelService;
import com.example.webapp.services.OfferService;
import com.example.webapp.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private ModelMapper modelMapper;
    private OfferService offerService;
    private ModelService modelService;
    private UserService userService;

    @Autowired
    public void setOfferService(OfferService offerService, ModelService modelService, UserService userService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
    }
    @ModelAttribute("offersModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("allOffers", offerService.allOffers());
        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("availableUsers", userService.allUsers());
        return "showAllOffers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("availableUsers", userService.allUsers());
        model.addAttribute("allOffers", offerService.allOffers());
        return "addOffer";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto addOfferDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("offersModel", addOfferDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offersModel",
                    result);
            return "redirect:/offers/add";
        }
        offerService.addOffer(addOfferDto);
        return "redirect:/offers/all";
    }

    @GetMapping("/offerDelete/{id}")
    public String removeOffer(@PathVariable("id") String id) {
        offerService.removeOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/showOfferInfo/{id}")
    public String showBrandsInfo(@PathVariable("id") String id, Model model) {
        model.addAttribute("offerInfo", offerService.showOfferInfo(id));

        return "showOfferInfo";
    }
}