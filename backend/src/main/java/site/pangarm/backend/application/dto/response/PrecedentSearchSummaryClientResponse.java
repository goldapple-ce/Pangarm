package site.pangarm.backend.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class PrecedentSearchSummaryClientResponse {
    @JsonProperty
    private Info info;
    @JsonProperty
    private Relate relate;
    @JsonProperty
    private Parties parties;
    @JsonProperty
    private OriginalJudgement originalJudgement;
    @JsonProperty
    private String purport;
    @JsonProperty
    private String fact;
    @JsonProperty
    private Opinion opinion;
    @JsonProperty
    private String judgement;
    @JsonProperty
    private String conclusion;
    @JsonProperty
    private String summary;

    @Getter
    static class Info {
        @JsonProperty
        private String caseNumber;
        @JsonProperty
        private String caseName;
        @JsonProperty
        private String judgementDate;
        @JsonProperty
        private Type type;

        @Getter
        static class Type {
            @JsonProperty
            private String incident;
            @JsonProperty
            private String courtName;
            @JsonProperty
            private String verdict;
        }
    }

    @Getter
    static class Relate {
        @JsonProperty
        private List<Law> lawList;
        @JsonProperty
        private List<Precedent> precedentList;

        @Getter
        static class Law {
            @JsonProperty
            private String lawName;
            @JsonProperty
            private String searchNumber;
            @JsonProperty
            private String searchName;
            @JsonProperty
            private String searchType;
            @JsonProperty
            private String searchKey;
        }

        @Getter
        static class Precedent {
            @JsonProperty
            private String caseNumber;
        }
    }

    @Getter
    static class Parties {
        @JsonProperty
        private String plaintiff;
        @JsonProperty
        private String defendant;
    }

    @Getter
    static class OriginalJudgement {
        @JsonProperty
        private String caseNumber;

    }

    @Getter
    static class Opinion {
        @JsonProperty
        private String plaintiff;
        @JsonProperty
        private String defendant;
    }
}
