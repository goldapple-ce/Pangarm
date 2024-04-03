package site.pangarm.backend.domain.precedent;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.precedent.entity.CaseNumber;
import site.pangarm.backend.domain.precedent.entity.Precedent;

import java.util.Optional;

interface PrecedentRepository extends JpaRepository<Precedent,Integer> {
    Optional<Precedent> findByCaseNumber(CaseNumber caseNumber);
    boolean existsByCaseNumber(CaseNumber caseNumber);
}
