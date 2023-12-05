package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Image;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.model.sto.*;

import java.util.List;

public interface StoService {
    StoResponse addSto(Long ltoId, AddStoRequest addStoRequest);

    Sto updateStoStatus(Long stoId, String status);

    Sto updateStoHitStatus(Long stoId, String status);

    List<StoResponse> getStoList(Long studentId);

    List<StoResponse> getStoListByLtoId(Long ltoId);

    boolean deleteSto(Long stoId);

    List<String> updateImageList(Long stoId, UpdateImageList updateImageList);

    Sto updateSto(Long stoId, UpdateStoRequest updateStoRequest);

    Sto updateStoRound(Long stoId, UpdateStoRoundRequest updateStoRoundRequest);

    Sto updateStoHitRound(Long stoId, UpdateStoRoundRequest updateStoRoundRequest);

}
