package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.entity.Image;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.model.sto.AddStoRequest;
import inu.thebite.toryaba.model.sto.UpdateImageList;
import inu.thebite.toryaba.model.sto.UpdateStoStatusRequest;
import inu.thebite.toryaba.service.StoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoController {

    private final StoService stoService;

    // add STO
    @PostMapping("/{ltoId}/sto/add")
    public Sto addSto(@PathVariable Long ltoId, @RequestBody AddStoRequest addStoRequest) {
        Sto sto = stoService.addSto(ltoId, addStoRequest);
        return sto;
    }

    // update STO status when STO status is stop or in progress
    @PatchMapping("{ltoId}/sto/{stoId}/update/status")
    public Sto updateStoStatus(@PathVariable Long ltoId,
                                          @PathVariable Long stoId,
                                          @RequestBody UpdateStoStatusRequest updateStoStatusRequest) {
        Sto sto = stoService.updateStoStatus(ltoId, stoId, updateStoStatusRequest);
        return sto;
    }

    // update STO status when STO status is git
    @PatchMapping("{ltoId}/sto/{stoId}/hit/update/status")
    public Sto updateStoHitStatus(@PathVariable Long ltoId,
                                             @PathVariable Long stoId,
                                             @RequestBody UpdateStoStatusRequest updateStoStatusRequest) {
        Sto sto = stoService.updateStoHitStatus(ltoId, stoId, updateStoStatusRequest);
        return sto;
    }

    // update image list(image url)
    @PatchMapping("{stoId}/image/list/update")
    public ResponseEntity updateImageList(@PathVariable Long stoId, @RequestBody UpdateImageList updateImageList) {
        List<Image> imageList = stoService.updateImageList(stoId, updateImageList);
        return ResponseEntity.ok(imageList);
    }

    // get STO list
    @GetMapping("{ltoId}/sto/list")
    public List<Sto> getStoList(@PathVariable Long ltoId) {
        List<Sto> stoList = stoService.getStoList(ltoId);
        return stoList;
    }

    // find STO (only one)
    @GetMapping("/find/{stoId}/sto")
    public Sto findSto(@PathVariable Long stoId) {
        Sto sto = stoService.findSto(stoId);
        return sto;
    }

    // delete STO
    @DeleteMapping("{ltoId}/sto/{stoId}/delete")
    public ResponseEntity deleteSto(@PathVariable Long ltoId, @PathVariable Long stoId) {
        stoService.deleteSto(ltoId, stoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}