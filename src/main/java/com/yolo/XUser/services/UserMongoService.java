package com.yolo.XUser.services;

import com.yolo.XUser.repository.api.UserRepository;
import com.yolo.XUser.resources.RatingVo;
import com.yolo.XUser.resources.User;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserMongoService {

    @Autowired
    UserRepository userRepository;

    public Mono<User> setRatings(User user, Double rating)
    {
        if (user.getRatings() != null) {

                    RatingVo rVo = new RatingVo(user.getUserId(), rating);
                    user.getRatings().add(rVo);
                    Integer size = user.getRatings().size();
                    Double sum = 0D;
                    for (RatingVo ratingVo : user.getRatings()) {
                        sum += ratingVo.getRating();
                    }
                    sum += rating;
                    Double aggr = sum / size;


                }
            else {
                RatingVo rVo = new RatingVo(user.getUserId(), rating);
                List<RatingVo> ratingList = new ArrayList<>();
                ratingList.add(rVo);
                user.setAggregateRating(rating);
            }
          return  userRepository.save(user);

        }

    public Mono<User> addFollower(User user, String followRequester) {
//        // TODO Auto-generated method stub

        if (null != user.getFollowers()) {
            if (!user.getFollowers().contains(followRequester)) {
                List<String> followers = user.getFollowers();
                followers.add(followRequester);
                user.setFollowers(followers);
                user.getPendingFollowApprovals().remove(followRequester);

            }
        } else {
            List<String> followers = new ArrayList<String>();
            followers.add(followRequester);
            user.setFollowers(followers);
            user.getPendingFollowApprovals().remove(followRequester);
        }
        return userRepository.save(user).retry();

    }

    public Mono<User> decrementFollower(User user, String userId) {
        // TODO Auto-generated method stub
if(user.getFollowers()!=null) {
    List<String> followers = user.getFollowers();
    followers.remove(userId);

}
        return userRepository.save(user);
    }

    public Mono<User> followRequest(User user, String followRequester) {

        if(null!=user.getPendingFollowApprovals())
        {
            if(!user.getPendingFollowApprovals().contains(followRequester))
            {
                user.getPendingFollowApprovals().add(followRequester);
            }
        }
            else
            {
                List<String> newFollowRequest= new ArrayList<>();
                newFollowRequest.add(followRequester);
                user.setPendingFollowApprovals(newFollowRequest);
            }

        return userRepository.save(user);
    }
}
