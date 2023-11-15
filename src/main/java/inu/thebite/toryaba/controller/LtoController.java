package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.lto.LtoRequest;
import inu.thebite.toryaba.model.lto.UpdateLtoStatusRequest;
import inu.thebite.toryaba.service.LtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LtoController {

    private final LtoService ltoService;

    // add LTO
    @PostMapping("/{domainId}/ltos")
    public Lto addLto(@PathVariable Long domainId, @RequestBody LtoRequest ltoRequest) {
        Lto lto = ltoService.addLto(domainId, ltoRequest);
        return lto;
    }

    // modified LTO status(stop, in progress)
    @PatchMapping("/ltos/{ltoId}/status")
    public Lto updateStatus(@PathVariable Long ltoId, @RequestBody UpdateLtoStatusRequest updateLtoStatusRequest) {
        Lto updateLto = ltoService.updateLtoStatus(ltoId, updateLtoStatusRequest);
        return updateLto;
    }

    //modified LTO status(hit)
    @PatchMapping("/ltos/{ltoId}/hit/status")
    public Lto updateHitStatus(@PathVariable Long ltoId, @RequestBody UpdateLtoStatusRequest updateLtoStatusRequest) {
        Lto updateLto = ltoService.updateLtoHitStatus(ltoId, updateLtoStatusRequest);
        return updateLto;
    }

    // update LTO contents
    @PatchMapping("/ltos/{ltoId}")
    public Lto updateLto(@PathVariable Long ltoId, @RequestBody LtoRequest ltoRequest) {
        Lto lto = ltoService.updateLto(ltoId, ltoRequest);
        return lto;
    }

    // get LTO List
    @GetMapping("/ltos")
    public List<Lto> getLtoList() {
        List<Lto> ltoList = ltoService.getLtoList();
        return ltoList;
    }

    @GetMapping("/ltos/{ltoId}/graphs")
    public List<LtoGraphResponse> getLtoGraph(@PathVariable Long ltoId) {
        List<LtoGraphResponse> response = ltoService.getLtoGraph(ltoId);
        return response;
    }

    // delete LTO
    @DeleteMapping("/ltos/{ltoId}")
    public ResponseEntity deleteLto(@PathVariable Long ltoId) {
        ltoService.deleteLto(ltoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}