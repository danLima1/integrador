package com.projeto.integrador.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Comment;
import com.projeto.integrador.Entity.Liker;
import com.projeto.integrador.Entity.Tweet;
import com.projeto.integrador.Entity.User;
import com.projeto.integrador.Repository.CommentRepository;
import com.projeto.integrador.Repository.LikerRepository;
import com.projeto.integrador.Repository.TweetRepository;
import com.projeto.integrador.exceptions.CommentNotFoundException;
import com.projeto.integrador.exceptions.TweetNotFoundException;
import com.projeto.integrador.exceptions.UserNotFoundException;

@Service
public class LikerService {

    @Autowired
    private LikerRepository likerRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;



    public Liker likeTweet(Long tweetId) throws TweetNotFoundException, UserNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if(loggedInUser == null) {
            throw new UserNotFoundException("User not logged in");
        }
        Liker existingLike = likerRepository.findByLikedTweetAndUser(tweet, loggedInUser);
        if(existingLike != null) {
            likerRepository.delete(existingLike);
            return null;
        } else {
            Liker like = new Liker();
            like.setLikedTweet(tweet);
            like.setUser(loggedInUser);
            return likerRepository.save(like);
        }
    }

    public boolean hasUserLikedTweet(Long tweetId, Long userId) {
        System.out.println(tweetId);
        System.out.println(userId);
        return likerRepository.existsByLikedTweetIdAndUserId(tweetId, userId);
    }

    public Liker likeComment(Long commentId, Liker like) throws CommentNotFoundException, UserNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if(loggedInUser == null) {
            throw new UserNotFoundException("User not logged in");
        }
        like.setLikedComment(comment);
        like.setUser(loggedInUser);
        return likerRepository.save(like);
    }





}