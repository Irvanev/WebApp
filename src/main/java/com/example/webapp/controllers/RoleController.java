package com.example.webapp.controllers;

import com.example.webapp.dtos.roles.AddRoleDto;
import com.example.webapp.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ModelAttribute("rolesModel")
    public AddRoleDto initRole() {
        return new AddRoleDto();
    }

    @GetMapping("/all")
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleService.allRoles());
        model.addAttribute("availableRole", roleService.allRoles());
        return "showAllRoles";
    }
    @GetMapping("/add")
    public String addRole() {
        return "addRole";
    }

    @PostMapping("/add")
    public String addRole(@Valid AddRoleDto addRoleDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("rolesModel", addRoleDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.modelsModel",
                    result);
            return "redirect:/roles/all";
        }
        roleService.addRole(addRoleDto);
        return "redirect:/roles/all";
    }
}
