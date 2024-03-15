package site.pangarm.backend.domain.precedent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PrecedentRepository extends JpaRepository<Precedent,Integer> {
    Optional<Precedent> findByCaseNumber(CaseNumber caseNumber);
    boolean existsByCaseNumber(CaseNumber caseNumber);
}
