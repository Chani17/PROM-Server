package inu.thebite.toryaba.service;


import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.lto.LtoRequest;
import inu.thebite.toryaba.model.lto.LtoResponse;
import inu.thebite.toryaba.model.lto.UpdateLtoStatusRequest;

import java.util.List;

public interface LtoService {

    LtoResponse addLto(Long domainId, Long studentId, LtoRequest ltoRequest);

    LtoResponse updateLtoStatus(Long ltoId, UpdateLtoStatusRequest updateLtoStatusRequest);

    List<LtoResponse> getLtoList(Long studentId);

    boolean deleteLto(Long ltoId);

    LtoResponse updateLtoHitStatus(Long ltoId, UpdateLtoStatusRequest updateLtoStatusRequest);

    LtoResponse updateLto(Long ltoId, LtoRequest ltoRequest);

    List<LtoGraphResponse> getLtoGraph(Long ltoId);

    List<LtoResponse> getLtoListByStudent(Long studentId, Long domainId);
}