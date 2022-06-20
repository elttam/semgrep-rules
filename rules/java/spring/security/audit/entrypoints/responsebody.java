import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/login")
public class LoginController {

    @RequestMapping
    public LoginStatus status(Authentication principal) {
        return principal == null ? new LoginStatus(false, null) : new LoginStatus(true, principal.getName());
    }
}

@RestController
public class CsrfController {

    @RequestMapping(value="/api/csrf",produces="application/json")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}

@RestController
public class SomethingController extends SomeEndpoint {

    static final String WHATEVER = "blahblah";
    static List<String> funkyList = new ArrayList<>();

    // ruleid: rest-ResponseBody
    @PostMapping("/PasswordReset/reset/login")
    @ResponseBody
    public AttackResult login(@RequestParam String password, @RequestParam String email) {
        if (WHATVER.equals(email)) {
            String password = funkyList[0];
        }
        return failed(this).feedback("login_failed").build();
    }

    @GetMapping("/PasswordReset/reset/reset-password/{link}")
    public ModelAndView resetPassword(@PathVariable(value = "link") String link, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (ResetLinkAssignment.resetLinks.contains(link)) {
            PasswordChangeForm form = new PasswordChangeForm();
            form.setResetLink(link);
            model.addAttribute("form", form);
            modelAndView.addObject("form", form);
            modelAndView.setViewName("password_reset"); //Display html page for changing password
        } else {
            modelAndView.setViewName("password_link_not_found");
        }
        return modelAndView;
    }

    @GetMapping("/PasswordReset/reset/change-password")
    public ModelAndView illegalCall() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("password_link_not_found");
        return modelAndView;
    }

    @PostMapping("/PasswordReset/reset/change-password")
    public ModelAndView changePassword(@ModelAttribute("form") PasswordChangeForm form, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (!org.springframework.util.StringUtils.hasText(form.getPassword())) {
            bindingResult.rejectValue("password", "not.empty");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("password_reset");
            return modelAndView;
        }
        if (!resetLinks.contains(form.getResetLink())) {
            modelAndView.setViewName("password_link_not_found");
            return modelAndView;
        }
        if (checkIfLinkIsFromTom(form.getResetLink())) {
            usersToTomPassword.put(getWebSession().getUserName(), form.getPassword());
        }
        modelAndView.setViewName("success");
        return modelAndView;
    }

    private boolean checkIfLinkIsFromTom(String resetLinkFromForm) {
        String resetLink = userToTomResetLink.getOrDefault(getWebSession().getUserName(), "unknown");
        return resetLink.equals(resetLinkFromForm);
    }
}



// https://spring.io/guides/gs/rest-service-cors/


package com.example.restservicecors;

//import java.util.concurrent.atomic.AtomicLong;

//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();
    // rulexid: xrest-CrossOrigin
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("==== get greeting ====");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
