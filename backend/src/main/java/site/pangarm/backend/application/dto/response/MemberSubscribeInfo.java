package site.pangarm.backend.application.dto.response;

import java.util.List;

public record MemberSubscribeInfo(String email, List<String> categoryList) {
}
