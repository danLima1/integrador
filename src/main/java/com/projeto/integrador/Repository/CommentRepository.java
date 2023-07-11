package com.projeto.integrador.Repository;

import com.projeto.integrador.Entity.Comment;
import com.projeto.integrador.Entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "SELECT * FROM comment WHERE tweet_id IN (SELECT id FROM tweet WHERE id = ?1) ORDER BY created_at DESC",nativeQuery = true)
    List<Comment> findByTweetId(Tweet tweet);


}