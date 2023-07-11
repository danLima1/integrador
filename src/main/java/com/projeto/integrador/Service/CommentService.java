package com.projeto.integrador.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Comment;
import com.projeto.integrador.Entity.Tweet;
import com.projeto.integrador.Entity.User;
import com.projeto.integrador.Repository.CommentRepository;
import com.projeto.integrador.Repository.TweetRepository;
import com.projeto.integrador.exceptions.CommentNotFoundException;
import com.projeto.integrador.exceptions.TweetNotFoundException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    public Comment createTweetComment(Long tweetId, Comment comment) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        comment.setTweet(tweet);
        comment.setUser(loggedInUser);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getTweetComments(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        return commentRepository.findByTweetId(tweet);
    }

    public void deleteComment(Long commentId) throws Exception {
        Comment deletedComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if (deletedComment.getUser() != loggedInUser) {
            throw new Exception("Not authorized to delete this comment");
        }
        commentRepository.delete(deletedComment);
    }

    public void updateComment(Long commentId, Comment comment) throws Exception {
        Comment updatedComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();

        if (updatedComment.getUser() != loggedInUser) {
            throw new Exception("Not authorized to edit this comment");
        }

        updatedComment.setText(comment.getText());
        updatedComment.setEdited(true);
        updatedComment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(updatedComment);
    }
}