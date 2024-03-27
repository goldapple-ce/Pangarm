package site.pangarm.backend.domain.caseType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class CaseTypeService {
    private final CaseTypeRepository caseTypeRepository;

    public CaseType findByName(String name){
        return caseTypeRepository.findByName(name).orElseThrow(()->
                new CaseTypeException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    public CaseType findByCode(int code){
        return caseTypeRepository.findById(code).orElseThrow(()->
                new CaseTypeException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    @Transactional
    public CaseType save(CaseType caseType){
        return caseTypeRepository.save(validation(caseType));
    }

    @Transactional
    public void saveAll(List<CaseType> caseTypeList){
        caseTypeRepository.saveAll(caseTypeList);
    }

    private CaseType validation(CaseType caseType){
        if(caseTypeRepository.existsById(caseType.getCode()))
            throw new CaseTypeException(ErrorCode.API_ERROR_ALREADY_EXIST);
        return caseType;
    }
}
