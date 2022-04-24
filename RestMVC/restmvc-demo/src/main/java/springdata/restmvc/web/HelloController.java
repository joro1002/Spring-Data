package springdata.restmvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/users/{userId}/posts/{postId}")
    public String getPost(@PathVariable Integer userId, @PathVariable Integer postId){
        return String.format("User: %d --> Post: %d%n", userId, postId);
    }
}
