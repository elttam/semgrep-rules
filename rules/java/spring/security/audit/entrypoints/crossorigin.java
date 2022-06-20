// https://spring.io/guides/gs/rest-service-cors/

//import java.util.concurrent.atomic.AtomicLong;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    // ruleid: rest-CrossOrigin
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("==== get greeting ====");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}

@RestController
@RequestMapping("/account")
public class AccountController {

    // ruleid: rest-CrossOrigin
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Account retrieve(@PathVariable Long id) {
        // ...
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void remove(@PathVariable Long id) {
        // ...
    }
}



// https://www.baeldung.com/spring-cors
// ruleid: rest-CrossOrigin
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Account retrieve(@PathVariable Long id) {
        // ...
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void remove(@PathVariable Long id) {
        // ...
    }
}
