package site.pangarm.backend.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.category.entity.Category;

import java.util.Optional;

interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    Optional<Category> findByName(String name);
}
