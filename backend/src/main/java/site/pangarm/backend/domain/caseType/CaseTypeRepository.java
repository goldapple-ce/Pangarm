package site.pangarm.backend.domain.caseType;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.caseType.entity.CaseType;

import java.util.Optional;

interface CaseTypeRepository extends JpaRepository<CaseType,Integer> {
    Optional<CaseType> findByName(String name);

    boolean existsByName(String name);
}
