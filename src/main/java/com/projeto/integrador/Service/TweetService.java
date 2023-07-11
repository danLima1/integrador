package com.projeto.integrador.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Follower;
import com.projeto.integrador.Entity.Tweet;
import com.projeto.integrador.Entity.User;
import com.projeto.integrador.Repository.FollowerRepository;
import com.projeto.integrador.Repository.LikerRepository;
import com.projeto.integrador.Repository.NotificationRepository;
import com.projeto.integrador.Repository.TweetRepository;
import com.projeto.integrador.exceptions.TweetNotFoundException;
import com.projeto.integrador.notification.Notification;
import com.projeto.integrador.notification.TweetPublisher;
import com.projeto.integrador.notification.UserObserver;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
private FollowerRepository followerRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private LikerRepository likerRepository;

    @Autowired
    private LikerService likerService;

    @Autowired
    private TweetPublisher tweetPublisher;

    @Autowired
    private NotificationRepository notificationRepository;


    public List<User> shareTweet(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        tweet.addSharedBy(loggedInUser);
        tweet = tweetRepository.save(tweet);
        return tweet.getSharedBy();
    }

    public void createTweet(Tweet tweet) {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        tweet.setUser(loggedInUser);
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUpdatedAt(LocalDateTime.now());
        tweet.setEdited(false);
        tweetRepository.save(tweet);

            List<Follower> followers = followerRepository.findByFollowing(loggedInUser);
            for(Follower follower : followers) {
                User userObserver = follower.getFollower();
                UserObserver observer = new UserObserver(userObserver);
                tweetPublisher.addObserver(observer);

                Notification notification = new Notification();
                notification.setRecipient(userObserver);
                notification.setTweet(tweet);
                notification.setRead(false);
                notificationRepository.save(notification);
            }
            tweetPublisher.notifyObservers(tweet);
    }

    public void updateTweet(Long tweetId, Tweet tweet) throws Exception {
        Tweet updatedTweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if (updatedTweet.getUser() != loggedInUser) {
            throw new Exception("You are not authorized to edit this tweet");
        }
        if (tweet.getMessage() != null && !tweet.getMessage().trim().isEmpty()) {
            updatedTweet.setMessage(tweet.getMessage());
        } else {
            throw new Exception("You need to provide a value for the tweet");
        }
        updatedTweet.setUpdatedAt(LocalDateTime.now());
        updatedTweet.setEdited(true);
        tweetRepository.save(updatedTweet);
    }

    public void deleteTweet(Long tweetId) throws Exception {
        Tweet deletedTweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();

        if (deletedTweet.getUser() != loggedInUser) {
            throw new Exception("You are not authorized to delete this tweet");
        }
        tweetRepository.delete(deletedTweet);
    }

    public Tweet findTweet(Long tweetId) throws TweetNotFoundException {
        return tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
    }

    public List<Tweet> getTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        for(Tweet tweet: tweets) {
            int likeCount = likerRepository.countByLikedTweet(tweet);
            tweet.setLikeCount(likeCount);
            if(loggedInUser != null) {
                tweet.setLikedByCurrentUser(likerService.hasUserLikedTweet(tweet.getId(), loggedInUser.getId()));
            } else {
                tweet.setLikedByCurrentUser(false);
            }

        }
        return tweets;
    }


    public List<Tweet> showUserTweets(Long userId){
        return tweetRepository.findLatestUserTweet(userId);
    }
}