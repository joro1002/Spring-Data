package springdata.restmvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.restmvc.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
