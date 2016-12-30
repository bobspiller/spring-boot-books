package rms.books.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rms.books.entity.Greeting;

import javax.jws.WebParam;

/**
 * Root context controller
 */
@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Greeting sayHi() {
        return sayHi("Boobie");
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public Greeting sayHi(@PathVariable String name) {
        return new Greeting(
                String.format("Welcome to the Book Store, %s!", name));
    }
}
