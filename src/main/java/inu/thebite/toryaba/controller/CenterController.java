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
    @PostMapping("/{directorId}")
    public Center addCenter(@PathVariable String directorId, @RequestBody CenterRequest centerRequest) {
        Center center = centerService.addCenter(directorId, centerRequest);
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
    @GetMapping("/{id}")
    public List<Center> getCenterList(@PathVariable String id) {
        List<Center> centerList = centerService.getCenterList(id);
        return centerList;
    }
}
