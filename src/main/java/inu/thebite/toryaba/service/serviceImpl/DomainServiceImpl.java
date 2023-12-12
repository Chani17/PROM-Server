package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Domain;
import inu.thebite.toryaba.model.domain.AddDomainRequest;
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

    @Override
    public Domain addDomain(AddDomainRequest addDomainRequest) {
        List<Domain> result = domainRepository.findAll();
        Domain domain = Domain.createDomain(result.size() + 1, addDomainRequest.getType(), addDomainRequest.getName(), addDomainRequest.getContents());
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
