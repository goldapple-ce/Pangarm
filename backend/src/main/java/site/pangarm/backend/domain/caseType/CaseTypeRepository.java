package site.pangarm.backend.domain.caseType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CaseTypeRepository extends JpaRepository<CaseType,Integer> {
    Optional<CaseType> findByName(String name);
}
