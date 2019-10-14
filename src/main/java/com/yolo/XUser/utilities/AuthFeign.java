package com.yolo.XUser.utilities;



import com.yolo.auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hendrik.prayogo on 3/10/2018.
 */
@FeignClient(name = "authfeign", url = "${service.auth.endpoint}",
configuration = AuthFeign.class)
public interface AuthFeign {
  @RequestMapping(
      value = "/api/v1/user",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ApiResponse<User>> getUserDetails(
          @RequestParam("userId") String userId);

}
