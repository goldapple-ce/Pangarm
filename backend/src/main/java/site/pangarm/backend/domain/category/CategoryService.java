package site.pangarm.backend.domain.category;

public interface CategoryService {

    Category save(Category category);

    void delete(int categoryId);

    Category update(int id, Category category);

    boolean existsByName(String name);

    Category findById(Integer categoryId) throws CategoryException;
}
