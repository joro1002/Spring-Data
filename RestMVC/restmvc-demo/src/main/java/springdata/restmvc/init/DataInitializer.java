package springdata.restmvc.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.restmvc.dao.PostRepository;
import springdata.restmvc.entity.Post;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0){
            List.of(
                    new Post("New in Spring", "String Boot is fancy", "Georgi",
                            Set.of("spring", "java", "spring boot")),
                    new Post("Reactive Spring", "Web Flux is here", "Georgi",
                            Set.of("spring", "java", "reactor")),
                    new Post("Easy to test", "WebTestClient is easy... ", "Georgi",
                            Set.of("spring", "java", "web test client"))
            ).forEach(postRepository::save);
        }

    }
}
