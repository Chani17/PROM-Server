package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.DomainRepository;
import inu.thebite.toryaba.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private final DomainRepository domainRepository;
    private final CenterRepository centerRepository;

    @Transactional
    @Override
    public Domain addDomain(Long centerId, AddDomainRequest addDomainRequest) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));

        List<Domain> result = domainRepository.findAllByCenterId(centerId);
        Domain domain = Domain.createDomain(result.size() + 1, addDomainRequest.getType(), addDomainRequest.getName(), addDomainRequest.getContents(), center);
        domainRepository.save(domain);
        return domain;
    }

    @Override
    public List<Domain> getDomainList() {
        List<Domain> domainList = domainRepository.findAll();
        return domainList;
    }

    @Transactional
    @Override
    public void deleteDomain(Long templateNum) {
        Domain domain = domainRepository.findById(templateNum).
                orElseThrow(() -> new IllegalStateException("해당하는 domain이 존재하지 않습니다."));

        domainRepository.deleteById(domain.getId());
    }
}
