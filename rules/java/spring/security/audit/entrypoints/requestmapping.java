import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// ruleid: rest-RequestMapping
@RestController
@RequestMapping("/api/login")
public class LoginController {

    // ruleid: rest-RequestMapping
    @RequestMapping
    public LoginStatus status(Authentication principal) {
        return principal == null ? new LoginStatus(false, null) : new LoginStatus(true, principal.getName());
    }
}

@RestController
public class CsrfController {

    // ruleid: rest-RequestMapping
    @RequestMapping(value="/api/csrf",produces="application/json")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}
