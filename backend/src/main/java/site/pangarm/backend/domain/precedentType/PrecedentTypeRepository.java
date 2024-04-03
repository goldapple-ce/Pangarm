package site.pangarm.backend.domain.precedentType;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;

import java.util.Optional;

interface PrecedentTypeRepository extends JpaRepository<PrecedentType,Integer> {
    Optional<PrecedentType> findByCaseTypeAndCourtNameAndVerdict(CaseType caseType,String courtName,String verdict);

}
