package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;
import inu.thebite.toryaba.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/domains")
public class DomainController {

    private final DomainService domainService;

    // add domain
    @PostMapping("/{centerId}")
    public void addDomain(@PathVariable Long centerId, @RequestBody AddDomainRequest addDomainRequest) {
        domainService.addDomain(centerId, addDomainRequest);
    }

    // get domain list
    @GetMapping("/{centerId}")
    public List<Domain> getDomainList(@PathVariable Long centerId) {
        List<Domain> domainList = domainService.getDomainList(centerId);
        return domainList;
    }

    // delete domain
    @DeleteMapping("/{domainId}")
    public ResponseEntity deleteDomain(@PathVariable Long domainId) {
        domainService.deleteDomain(domainId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
