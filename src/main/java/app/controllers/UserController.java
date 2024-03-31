package app.controllers;

import app.model.User;
import app.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/showAll")
    public String showAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "showAll";
    }

    @GetMapping("/new")
    public String showFormForSave(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/new")
    public String saveUser(@RequestParam String name, @RequestParam String lastname, @RequestParam int age) {
        userService.saveUser(new User(name, lastname, age));
        return "redirect:/showAll";
    }

}
