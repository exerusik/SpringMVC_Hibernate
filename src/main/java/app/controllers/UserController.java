package app.controllers;

import app.model.User;
import app.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
