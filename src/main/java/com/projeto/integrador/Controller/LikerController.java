package com.projeto.integrador.Controller;

import com.projeto.integrador.model.LikerModel;
import com.projeto.integrador.Entity.Liker;
import com.projeto.integrador.exceptions.CommentNotFoundException;
import com.projeto.integrador.exceptions.TweetNotFoundException;
import com.projeto.integrador.Service.LikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LikerController {

    @Autowired
    private LikerService likeService;

    @PostMapping("/like/tweet/{tweetId}")
    public Liker likeTweet(@PathVariable("tweetId") Long tweetId) throws TweetNotFoundException {
        return likeService.likeTweet(tweetId);
    }
    @PostMapping("/like/comment/{commentId}")
    public Liker likeComment(@PathVariable("commentId") Long commentId) throws CommentNotFoundException {
        return likeService.likeComment(commentId);
    }
    @DeleteMapping("/unlike/tweet/{tweetId}")
    public void unlikeTweet(@PathVariable("tweetId") Long tweetId) throws TweetNotFoundException {
        likeService.unlikeTweet(tweetId);
    }
    @DeleteMapping("/unlike/comment/{commentId}")
    public void unlikeComment(@PathVariable("commentId") Long commentId) throws CommentNotFoundException {
        likeService.unlikeComment(commentId);
    }
    @GetMapping("/tweet/{tweetId}/likes")
    public List<LikerModel> getTweetLikes(@PathVariable("tweetId") Long tweetId) throws TweetNotFoundException {
        return likeService.getTweetLikes(tweetId)
                .stream()
                .map(LikerModel::mapLikerToModel).collect(Collectors.toList());
    }

    @GetMapping("/comment/{commentId}/likes")
    public List<LikerModel> getCommentLikes(@PathVariable("commentId") Long commentId) throws CommentNotFoundException {
        return likeService.getCommentLikes(commentId)
                .stream()
                .map(LikerModel::mapLikerToModel).collect(Collectors.toList());
    }
}