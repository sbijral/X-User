package com.yolo.XUser.controllers;

import com.yolo.XUser.repository.api.CategoryRepository;
import com.yolo.XUser.repository.api.CityRepository;
import com.yolo.XUser.repository.api.UserRepository;
import com.yolo.XUser.resources.Category;
import com.yolo.XUser.resources.City;
import com.yolo.XUser.resources.RatingVo;
import com.yolo.XUser.resources.User;
import com.yolo.XUser.services.UserMongoService;
import com.yolo.XUser.utilities.ApiResponse;
import com.yolo.XUser.utilities.AuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Flux;
import rx.Single;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserMongoService userService;
    
    @Autowired
    AuthFeign authFeign;

    @GetMapping("/user/getUser/{userId}")
    public DeferredResult<ApiResponse<User>> getUserDetails(@PathVariable String userId) {
       //Call X-Auth for Info and other info from mongo
        ResponseEntity<ApiResponse<com.yolo.auth.model.User>> response = authFeign.getUserDetails(userId);

        return null;
    }

    @GetMapping("/user/getSelf/{userId}")
    public DeferredResult<ApiResponse<User>> getSelfDetails(@PathVariable String userId) {
        //Take auth info from token and other info from mongo

        return null;
    }


    @PutMapping("/user/{userId}/addFollower/{followRequester}")
    public DeferredResult<ApiResponse<Void>> addFollower(@PathVariable String userId, @PathVariable String followRequester) {

        //This will be called by the user to whom the request was sent and the requesterID will be added to his followerlist
        ApiResponse<Void> result = new ApiResponse<>();
        DeferredResult<ApiResponse<Void>> defResult = new DeferredResult<>();
        userRepository.findById(userId).
                doOnSuccess(user->
                {
                    if(user!=null)
                    {
                    userService.addFollower(user,followRequester).doOnSuccess(
                            user1 -> {
                                result.setStatus(HttpStatus.OK);
                                result.setMessage("Successfully followed the User " + userId);
                                //  result.setDebugMessage(error.getCause().toString());
                                defResult.setResult(result);
                            }
                    )
                            .subscribe();

                }
                    else {
                        result.setStatus(HttpStatus.NOT_FOUND);
                        result.setMessage("Not able to find:: " + userId);
                        //  result.setDebugMessage(error.getCause().toString());
                        defResult.setResult(result);

                    }
                }).subscribe();

        return defResult;
    }

    @PutMapping("/user/{followRequester}/followRequest/{userId}")
    public DeferredResult<ApiResponse<Void>> followRequest(@PathVariable String userId, @PathVariable String followRequester) {

        //This will be sent by a user to send follow request to the desired user
        ApiResponse<Void> result = new ApiResponse<>();
        DeferredResult<ApiResponse<Void>> defResult = new DeferredResult<>();
        userRepository.findById(userId).doOnSuccess(user->
                {
                    if (user != null){
                        userService.followRequest(user, followRequester).doOnSuccess(user1 -> {
                            result.setStatus(HttpStatus.OK);
                            result.setMessage("Successfully rated the User " + userId);
                            //  result.setDebugMessage(error.getCause().toString());
                            defResult.setResult(result);
                            //send notification to user
                        }).subscribe();
                }
    }
            )
        .subscribe();
        // Send notification to User
        return defResult;
    }

    @PutMapping("/user/{userId}/removeFollower/{tobeUnFollowedUserId}")
    public DeferredResult<ApiResponse<Void>> removeFollower(@PathVariable String userId, @PathVariable String tobeUnFollowedUserId) {

        ApiResponse<Void> result = new ApiResponse<>();
        DeferredResult<ApiResponse<Void>> defResult = new DeferredResult<>();
        userRepository.findById(userId).
                doOnSuccess(user->
                {
                    if(user!=null)
                    {
                        userService.decrementFollower(user,tobeUnFollowedUserId).doOnSuccess(
                                user1 -> {
                                    result.setStatus(HttpStatus.OK);
                                    result.setMessage("Successfully followed the User " + userId);
                                    //  result.setDebugMessage(error.getCause().toString());
                                    defResult.setResult(result);
                                }
                        )
                                .subscribe();

                    }
                    else {
                        result.setStatus(HttpStatus.NOT_FOUND);
                        result.setMessage("Not able to find:: " + userId);
                        //  result.setDebugMessage(error.getCause().toString());
                        defResult.setResult(result);

                    }
                }).subscribe();

        return defResult;
    }

    @GetMapping("user/getAllCities")
    public Flux<City> getAllCities()
    {
        return cityRepository.findAll();
    }

    @GetMapping("user/getAllCategories")
    public Flux<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @PutMapping("/user/{userId}/giveRating/{userToBeRated}/{rating}")
    public DeferredResult<ApiResponse<Void>> giveRating(@PathVariable String userToBeRated, @PathVariable String userId, @PathVariable Double rating)
    {
        ApiResponse<Void> result = new ApiResponse<>();
        DeferredResult<ApiResponse<Void>> defResult = new DeferredResult<>();

        userRepository.findById(userToBeRated).
                doOnSuccess(user -> {
                    if (user != null&& !user.getRatings().contains(userId))
                    {
                        userService.setRatings(user,rating)
                                .doOnSuccess(user1 ->
                                {
                                    result.setStatus(HttpStatus.OK);
                                    result.setMessage("Successfully rated the User " + userId);
                                    //  result.setDebugMessage(error.getCause().toString());
                                    defResult.setResult(result);
                                })
                                .subscribe();
                    }
                    else
                    {
                        result.setStatus(HttpStatus.NOT_MODIFIED);
                        result.setMessage("Not ABle to find the User Or Already rated the User " + userId);
                        //  result.setDebugMessage(error.getCause().toString());
                        defResult.setResult(result);
                    }
                })
                .doOnError(error->
                        {
                            result.setStatus(HttpStatus.NOT_MODIFIED);
                            result.setMessage("Not ABle to rate the User " + userId);
                            result.setDebugMessage(error.getCause().toString());
                            defResult.setResult(result);
                        }
                )
                .subscribe();
return defResult;

    }

    //add category api

}
