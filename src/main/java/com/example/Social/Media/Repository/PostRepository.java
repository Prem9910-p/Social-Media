package com.example.Social.Media.Repository;

import com.example.Social.Media.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post , Integer> {

    @Query("select p from Post p where p.user.id= :userId")
    List<Post> findPostByUserId(Integer userId);
}
