package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;

import java.util.List;

public interface DomainService {
    Domain addDomain(AddDomainRequest addDomainRequest);

    List<Domain> getDomainList();

    void deleteDomain(Long templateNum);
}
