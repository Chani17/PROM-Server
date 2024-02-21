package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;
import inu.thebite.toryaba.model.domain.DomainResponse;
import inu.thebite.toryaba.model.domain.UpdateDomainRequest;
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

    @Override
    public void addDomain(Long centerId, AddDomainRequest addDomainRequest) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));
        List<Domain> result = domainRepository.findByCenterId(center.getId());
        Domain domain = Domain.createDomain(result.size() + 1, addDomainRequest.getName(), center);
        domainRepository.save(domain);
    }

    @Override
    public List<Domain> getDomainList(Long centerId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));
        List<Domain> domainList = domainRepository.findByCenterId(center.getId());
        return domainList;
    }

    @Transactional
    @Override
    public void deleteDomain(Long domainId) {
        Domain domain = domainRepository.findById(domainId).
                orElseThrow(() -> new IllegalStateException("해당하는 domain이 존재하지 않습니다."));

        domainRepository.deleteById(domain.getId());
    }

    @Transactional
    @Override
    public DomainResponse updateDomain(Long domainId, UpdateDomainRequest updateDomainRequest) {
        Domain domain = domainRepository.findById(domainId).
                orElseThrow(() -> new IllegalStateException("해당하는 domain이 존재하지 않습니다."));

        domain.updateDomain(updateDomainRequest.getName());
        return DomainResponse.response(domain.getId(), domain.getTemplateNumber(), domain.getName(), domain.getRegisterDate(), domain.getCenter().getId());
    }
}
