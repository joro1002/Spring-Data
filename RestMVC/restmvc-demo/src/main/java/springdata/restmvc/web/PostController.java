package springdata.restmvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springdata.restmvc.dao.PostRepository;
import springdata.restmvc.entity.Post;

import java.util.Collection;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public Collection<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow();
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Post addPost(@RequestBody Post post){
//        return postRepository.save(post);
//    }
}
