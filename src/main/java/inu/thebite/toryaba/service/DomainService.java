package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;

import java.util.List;

public interface DomainService {
    Domain addDomain(Long centerId, AddDomainRequest addDomainRequest);

    List<Domain> getDomainList(Long centerId);

    void deleteDomain(Long templateNum);
}
