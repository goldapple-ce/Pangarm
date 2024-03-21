package site.pangarm.backend.domain.membercategory;

public interface MemberCategoryService {
    void save(int memberId, int categoryId);
    void delete(int memberId, int categoryId);

}
