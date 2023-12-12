package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Lto;
import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.lto.LtoRequest;
import inu.thebite.toryaba.model.lto.LtoResponse;
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
    @PostMapping("/{domainId}/ltos/{studentId}")
    public LtoResponse addLto(@PathVariable Long domainId, @PathVariable Long studentId, @RequestBody LtoRequest ltoRequest) {
        LtoResponse lto = ltoService.addLto(domainId, studentId, ltoRequest);
        return lto;
    }

    // modified LTO status(stop, in progress)
    @PatchMapping("/ltos/{ltoId}/status")
    public LtoResponse updateStatus(@PathVariable Long ltoId, @RequestBody UpdateLtoStatusRequest updateLtoStatusRequest) {
        LtoResponse updateLto = ltoService.updateLtoStatus(ltoId, updateLtoStatusRequest);
        return updateLto;
    }

    //modified LTO status(hit)
    @PatchMapping("/ltos/{ltoId}/hit/status")
    public LtoResponse updateHitStatus(@PathVariable Long ltoId, @RequestBody UpdateLtoStatusRequest updateLtoStatusRequest) {
        LtoResponse updateLto = ltoService.updateLtoHitStatus(ltoId, updateLtoStatusRequest);
        return updateLto;
    }

    // update LTO contents
    @PatchMapping("/ltos/{ltoId}")
    public LtoResponse updateLto(@PathVariable Long ltoId, @RequestBody LtoRequest ltoRequest) {
        LtoResponse lto = ltoService.updateLto(ltoId, ltoRequest);
        return lto;
    }

    // get LTO List
    @GetMapping("/{studentId}/ltos")
    public List<LtoResponse> getLtoList(@PathVariable Long studentId) {
        List<LtoResponse> ltoList = ltoService.getLtoList(studentId);
        return ltoList;
    }

    @GetMapping("/{domainId}/{studentId}/ltos")
    public List<LtoResponse> getLtoListByStudent(@PathVariable Long studentId, @PathVariable Long domainId) {
        List<LtoResponse> ltoList = ltoService.getLtoListByStudent(studentId, domainId);
        return ltoList;
    }


    @GetMapping("/ltos/{ltoId}/graphs")
    public List<LtoGraphResponse> getLtoGraph(@PathVariable Long ltoId) {
        List<LtoGraphResponse> response = ltoService.getLtoGraph(ltoId);
        return response;
    }

    // delete LTO
    @DeleteMapping("/ltos/{ltoId}")
    public boolean deleteLto(@PathVariable Long ltoId) {
        boolean result = ltoService.deleteLto(ltoId);
        return result;
    }
}