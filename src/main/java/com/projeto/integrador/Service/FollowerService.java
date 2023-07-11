package com.projeto.integrador.Service;

import com.projeto.integrador.Entity.Follower;
import com.projeto.integrador.Entity.User;
import com.projeto.integrador.exceptions.UserNotFoundException;
import com.projeto.integrador.notification.TweetPublisher;
import com.projeto.integrador.Repository.FollowerRepository;
import com.projeto.integrador.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
private TweetPublisher tweetPublisher;

    public boolean toggleFollowUser(Long userId) throws UserNotFoundException {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        User followee = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error 404 - User not found"));

        if (loggedInUser != null && followee != null) {
            Follower existingFollower = followerRepository.findByFollowerAndFollowing(loggedInUser, followee);
            if (existingFollower != null) {
                followerRepository.delete(existingFollower);
                return false;
            } else {
                Follower newFollower = new Follower();
                newFollower.setFollower(loggedInUser);
                newFollower.setFollowing(followee);
                followerRepository.save(newFollower);
                return true;
            }
        }
        return false;
    }

    public boolean isUserFollowing(Long profileUserId) {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        User profileUser = userRepository.findById(profileUserId).orElse(null);

        if (loggedInUser != null && profileUser != null) {
            Follower existingFollower = followerRepository.findByFollowerAndFollowing(loggedInUser, profileUser);
            return existingFollower != null;
        }

        return false;
    }

    public long getNumberOfFollowers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return followerRepository.countByFollowing(user);
        }
        return 0;
    }

    public long getNumberOfFollowings(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return followerRepository.countByFollower(user);
        }
        return 0;
    }

}