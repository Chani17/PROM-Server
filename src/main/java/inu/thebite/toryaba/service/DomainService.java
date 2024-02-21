package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;
import inu.thebite.toryaba.model.domain.DomainResponse;
import inu.thebite.toryaba.model.domain.UpdateDomainRequest;

import java.util.List;

public interface DomainService {
    void addDomain(Long centerId, AddDomainRequest addDomainRequest);

    List<Domain> getDomainList(Long centerId);

    void deleteDomain(Long templateNum);

    DomainResponse updateDomain(Long domainId, UpdateDomainRequest updateDomainRequest);
}
