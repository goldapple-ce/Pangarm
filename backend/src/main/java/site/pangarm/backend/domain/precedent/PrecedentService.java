package site.pangarm.backend.domain.precedent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.precedentType.PrecedentType;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PrecedentService {

    private final PrecedentRepository precedentRepository;
    private final CaseTypeService caseTypeService;

    public Precedent findByCaseNumber(String caseNumber) {
        return precedentRepository.findByCaseNumber(validation(caseNumber))
                .orElseThrow(()->new PrecedentException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    public Precedent findById(int id){
        return precedentRepository.findById(id)
                .orElseThrow(()->new PrecedentException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    @Transactional
    public Precedent save(String strCaseNumber, String caseName, LocalDate judgementDate, PrecedentType precedentType){
        CaseNumber caseNumber = validation(strCaseNumber);
        return precedentRepository.save(Precedent.of(validation(caseNumber),caseName, judgementDate,precedentType));
    }

    private CaseNumber validation(CaseNumber caseNumber){
        if(precedentRepository.existsByCaseNumber(caseNumber))
            throw new PrecedentException(ErrorCode.API_ERROR_ALREADY_EXIST);
        return caseNumber;
    }

    private CaseNumber validation(String caseNumber){
        Pattern pattern = Pattern.compile("(\\d+)(\\D+)(\\d+)");
        Matcher matcher = pattern.matcher(caseNumber);

        if(matcher.find()){
            int caseYear = Integer.parseInt(matcher.group(1));
            String caseTypeName = matcher.group(2);
            int registrationNumber = Integer.parseInt(matcher.group(3));

            CaseType caseType = caseTypeService.findByName(caseTypeName);

            return CaseNumber.of(caseYear,caseType,registrationNumber);
        }else throw new BusinessException(ErrorCode.API_ERROR_INPUT_INVALID_VALUE);
    }
}
