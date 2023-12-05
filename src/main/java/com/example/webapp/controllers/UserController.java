package com.example.webapp.controllers;


import com.example.webapp.dtos.users.AddUserDto;
import com.example.webapp.services.RoleService;
import com.example.webapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute("usersModel")
    public AddUserDto iniUser() {
        return new AddUserDto();
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("usersAll", userService.allUsers());
        model.addAttribute("availableRole", roleService.allRoles());
        return "showAllUsers";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("availableUsers", userService.allUsers());
        model.addAttribute("availableRole", roleService.allRoles());
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto addUserDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("usersModel", addUserDto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.usersModel",
                    result);
            return "redirect:/users/add";
        }
        userService.addUser(addUserDto);
        return "redirect:/users/all";
    }

    @GetMapping("/userDelete/{name}")
    public String removeUser(@PathVariable("name") String userName) {
        userService.removeUser(userName);

        return "redirect:/users/all";
    }

    @GetMapping("/showUserInfo/{name}")
    public String showUserInfo(@PathVariable("name") String name, Model model) {
        model.addAttribute("usersInfo", userService.showUserInfo(name));

        return "showUserInfo";
    }
}
