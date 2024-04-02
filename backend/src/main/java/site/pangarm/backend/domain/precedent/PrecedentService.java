package site.pangarm.backend.domain.precedent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.precedent.entity.CaseNumber;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.global.error.ErrorCode;

import java.time.LocalDate;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PrecedentService {

    private final PrecedentRepository precedentRepository;

    public Precedent findByCaseNumber(String caseNumber) {
        return precedentRepository.findByCaseNumber(CaseNumber.of(caseNumber))
                .orElseThrow(()->new PrecedentException(ErrorCode.API_ERROR_PRECEDENT_NOT_FOUND));
    }

    public Precedent findById(int id){
        return precedentRepository.findById(id)
                .orElseThrow(()->new PrecedentException(ErrorCode.API_ERROR_PRECEDENT_NOT_FOUND));
    }

    @Transactional
    public Precedent save(String strCaseNumber, String caseName, LocalDate judgementDate, String summary, PrecedentType precedentType){
        CaseNumber caseNumber = CaseNumber.of(strCaseNumber);
        return precedentRepository.findByCaseNumber(caseNumber).orElseGet(()->
                precedentRepository.save(Precedent.of(caseNumber,caseName, judgementDate,summary,precedentType)));
    }

    @Transactional
    public Precedent save(Precedent precedent){
        return precedentRepository.findByCaseNumber(precedent.getCaseNumber()).orElseGet(()->
                precedentRepository.save(precedent));
    }

}
