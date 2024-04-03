package site.pangarm.backend.domain.precedentType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PrecedentTypeService {
    private final PrecedentTypeRepository precedentTypeRepository;

    @Transactional
    public PrecedentType save(CaseType caseType, String courtName, String verdict){
        return precedentTypeRepository.findByCaseTypeAndCourtNameAndVerdict(caseType, courtName, verdict).orElseGet(()->
                precedentTypeRepository.save(PrecedentType.of(caseType, courtName, verdict))
        );
    }

    @Transactional
    public PrecedentType save(PrecedentType precedentType){
        return precedentTypeRepository.findByCaseTypeAndCourtNameAndVerdict(precedentType.getCaseType(), precedentType.getCourtName(), precedentType.getVerdict()).orElseGet(()->
                precedentTypeRepository.save(precedentType)
        );
    }

    public PrecedentType findBy(CaseType caseType,String courtName,String verdict){
        return precedentTypeRepository
                .findByCaseTypeAndCourtNameAndVerdict(caseType, courtName, verdict)
                .orElseThrow(()-> new PrecedentTypeException(ErrorCode.API_ERROR_PRECEDENT_TYPE_NOT_FOUND));
    }

}
