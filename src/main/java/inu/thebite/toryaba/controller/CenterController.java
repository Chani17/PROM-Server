package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CenterController {

    private final CenterService centerService;


    // add center
    @PostMapping("/center")
    public Center addCenter(@LoginMember User user, @RequestBody CenterRequest centerRequest) {
        String userId = user.getUsername();
        Center center = centerService.addCenter(userId, centerRequest);
        return center;
    }

    // update center
    @PatchMapping("/center/{centerId}")
    public Center updateCenter(@LoginMember User user, @PathVariable Long centerId, @RequestBody CenterRequest centerRequest) {
        String userId = user.getUsername();
        Center center = centerService.updateCenter(userId, centerId, centerRequest);
        return center;
    }


    // delete center
    @DeleteMapping("/center/{centerId}")
    public boolean deleteCenter(@LoginMember User user, @PathVariable Long centerId) {
        String userId = user.getUsername();
        boolean result = centerService.deleteCenter(userId, centerId);
        return result;
    }

    // get center list
    @GetMapping("/centers")
    public List<Center> getCenterList(@LoginMember User user) {
        String userId = user.getUsername();
        List<Center> centerList = centerService.getCenterList(userId);
        return centerList;
    }
}
