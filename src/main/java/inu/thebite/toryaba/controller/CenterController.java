package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    // add center
    @PostMapping("/centers")
    public Center addCenter(@LoginMember User user, @RequestBody CenterRequest centerRequest) {
        log.trace("addCenter 들어옴");
        log.trace("user = {}", user.getAuthorities().stream().toList());
        String userId = user.getUsername();
        log.trace("userId = {}", userId);
        Center center = centerService.addCenter(userId, centerRequest);
        log.trace("center = {}", center);
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
