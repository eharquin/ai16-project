package fr.utc.communicator.controllers.admin;

import fr.utc.communicator.models.User;
import fr.utc.communicator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin/users")
    public ModelAndView index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) throws Exception {
        Page<User> users = userRepository.findAll(PageRequest.of(page, 2));
        ModelAndView mav = new ModelAndView("admin/users/index");
        String previousPageUrl = page > 0
                ? request.getRequestURL().toString() + "?page=" + (page - 1)
                : null;
        String nextPageUrl = request.getRequestURL().toString() + "?page=" + (page + 1);
        mav.addObject("users", users);
        mav.addObject("page", page);
        mav.addObject("previousPageUrl", previousPageUrl);
        mav.addObject("nextPageUrl", nextPageUrl);
        return mav;
    }

    @GetMapping("/admin/users/create")
    public ModelAndView create() throws Exception {
        ModelAndView mav = new ModelAndView("admin/users/create");
        return mav;
    }

    @PostMapping("/admin/users")
    public String store(@RequestParam("username") String username, @RequestParam("mail") String mail, @RequestParam("password") String password) {
        User user = userRepository.findByMail(mail);
        if (user == null) {
            user = new User();
            user.setName(username);
            user.setMail(mail);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        } else {
            System.out.println(mail + " already exist.");
        }

        return "redirect:/admin/users";
    }
}