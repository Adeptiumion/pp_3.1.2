package pp_312.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import pp_312.model.User;
import pp_312.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final Validator validator;

    @Autowired
    public UsersController(UserService userService, @Qualifier("userValidator") Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }


    @GetMapping("/create_user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/create_new_user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "users/create_new_user";

        userService.create(user);
        return "redirect:/users"; // Редиректим на главную.
    }

    @GetMapping("/user_by_id")
    public String userPage(@RequestParam("id") String id, Model model) {
        model.addAttribute("user_with_id", userService.readOne(Integer.parseInt(id)));
        return "users/update_user";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.readAll());

        return "users/index";
    }

    @PostMapping("/update")
    public String update
            (
                    @RequestParam("id") String id,
                    @ModelAttribute("user_with_id") @Valid User user,
                    BindingResult bindingResult
            ) {
        validator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "users/update_user";


        userService.update(Integer.parseInt(id), user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        userService.delete(Integer.parseInt(id));
        return "redirect:/users";
    }

    @PostMapping("/truncate_all")
    public String truncate() {
        userService.truncateAll();
        return "redirect:/users";
    }


}
