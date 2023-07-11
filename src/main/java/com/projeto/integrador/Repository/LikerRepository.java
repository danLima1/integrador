package com.projeto.integrador.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.integrador.Entity.Comment;
import com.projeto.integrador.Entity.Liker;
import com.projeto.integrador.Entity.Tweet;
import com.projeto.integrador.Entity.User;


@Repository
public interface LikerRepository extends JpaRepository<Liker, Long> {
    @Query(value = "SELECT l from Liker l WHERE l.likedTweet = ?1")
    List<Liker> getTweetLikes(Tweet tweet);

    @Query(value = "SELECT l FROM Liker l WHERE l.likedComment = ?1")
    List<Liker> getCommentLikes(Comment comment);

    Liker findByLikedTweetAndUser(Tweet likedTweet, User user);

    Liker findByLikedCommentAndUser(Comment likedComment, User user);

    Liker findByLikedTweet(Tweet likedTweet);

    Liker findByUser(User user);
}