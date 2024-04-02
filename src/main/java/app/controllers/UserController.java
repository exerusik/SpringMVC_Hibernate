package app.controllers;

import app.model.User;
import app.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(new User(user.getName(), user.getLastname(), user.getAge()));
        return "redirect:/showAll";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/showAll";
    }

    @PostMapping("/edit")
    public String showFormForEdit(@RequestParam(name = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute(user);
        return "editUser";
    }

    @PostMapping("/userEdit")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);

        return "redirect:/showAll";


    }
}
