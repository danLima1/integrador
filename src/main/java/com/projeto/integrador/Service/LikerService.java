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



    public Liker likeTweet(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();

        Liker like = new Liker();
        like.setLikedTweet(tweet);
        like.setUser(loggedInUser);
        tweet.ifTweetIsLiked();
        likerRepository.save(like);
        return like;
    }

    public Liker likeComment(Long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        Liker like = new Liker();
        like.setLikedComment(comment);
        like.setUser(loggedInUser);
        likerRepository.save(like);
        return like;
    }

    public void unlikeTweet(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        Liker like = likerRepository.findByUser(loggedInUser);
        tweet.ifTweetIsUnliked();
        likerRepository.delete(like);
    }

    public void unlikeComment(Long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        Liker unlike = likerRepository.findByLikedCommentAndUser(comment, loggedInUser);
        likerRepository.delete(unlike);
    }

    public List<Liker> getTweetLikes(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));

        return likerRepository.getTweetLikes(tweet);
    }

    public List<Liker> getCommentLikes(Long commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist") );
        return likerRepository.getCommentLikes(comment);
    }

}