package site.pangarm.backend.application.facade;

import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.member.entity.Member;

public interface SubscribeFacade {

    void subscribe(int memberId, String categoryName) ;

    void unsubscribe(int memberId, String categoryName);
}
