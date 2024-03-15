package site.pangarm.backend.domain.viewingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmark;

public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, ViewingHistoryId> {
}
