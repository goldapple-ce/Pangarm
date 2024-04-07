package site.pangarm.backend.application.facade;

import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.dto.response.MemberFindByIdResponse;
import site.pangarm.backend.application.dto.response.MemberSubscribeInfo;
import site.pangarm.backend.application.dto.response.PrecedentSearchHistoryResponse;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

import java.util.List;

public interface MemberFacade {

    @Transactional
    public void signup(MemberSignUpRequest request) ;

    public PrecedentSearchHistoryResponse findAllSearchHistory(User user);

    @Transactional
    public void bookmarkPrecedent(User user, int precedentId) ;

    public MemberFindByIdResponse getById(int userId) ;


    public List<String> getCategoryList(int memberId) ;

    public List<MemberSubscribeInfo> getAllMemberSubscribeInfoList() ;



}
