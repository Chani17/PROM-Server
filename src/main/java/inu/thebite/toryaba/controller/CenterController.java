package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    // add center
    @PostMapping("/centers")
    public Center addCenter(@LoginMember User user, @RequestBody CenterRequest centerRequest) {
        System.out.println("addCenter 들어옴");
        System.out.println("User = " + user);
        String userId = user.getUsername();
        System.out.println("userId = " + userId);
        Center center = centerService.addCenter(userId, centerRequest);
        System.out.println("center = " + center);
        return center;
    }

    // update center
    @PatchMapping("/centers/{centerId}")
    public Center updateCenter(@PathVariable Long centerId, @RequestBody CenterRequest centerRequest) {
        Center center = centerService.updateCenter(centerId, centerRequest);
        return center;
    }


    // delete center
    @DeleteMapping("/centers/{centerId}")
    public ResponseEntity deleteCenter(@PathVariable Long centerId) {
        centerService.deleteCenter(centerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get center list
    @GetMapping("/center/{id}")
    public List<Center> getCenterList(@PathVariable String id) {
        List<Center> centerList = centerService.getCenterList(id);
        return centerList;
    }
}
