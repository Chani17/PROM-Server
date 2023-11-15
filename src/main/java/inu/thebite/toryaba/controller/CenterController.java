package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.model.center.CenterRequest;
import inu.thebite.toryaba.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/centers")
public class CenterController {

    private final CenterService centerService;

    // add center
    @PostMapping("/{principalId}/add")
    public Center addCenter(@PathVariable String principalId, @RequestBody CenterRequest centerRequest) {
        Center center = centerService.addCenter(principalId, centerRequest);
        return center;
    }

    // update center
    @PatchMapping("/{centerId}")
    public Center updateCenter(@PathVariable Long centerId, @RequestBody CenterRequest centerRequest) {
        Center center = centerService.updateCenter(centerId, centerRequest);
        return center;
    }


    // delete center
    @DeleteMapping("/{centerId}")
    public ResponseEntity deleteCenter(@PathVariable Long centerId) {
        centerService.deleteCenter(centerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // get center list
    @GetMapping()
    public List<Center> getCenterList() {
        List<Center> centerList = centerService.getCenterList();
        return centerList;
    }
}
