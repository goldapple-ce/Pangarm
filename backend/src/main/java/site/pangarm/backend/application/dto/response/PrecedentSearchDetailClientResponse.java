package site.pangarm.backend.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public class PrecedentSearchDetailClientResponse {
    @JsonProperty
    private Info info;
    @JsonProperty
    private Relate relate;
    @JsonProperty
    private String body;
    @JsonProperty
    private boolean isPdf;

    @Getter
    public static class Info {
        @JsonProperty
        private String caseNumber;
        @JsonProperty
        private String caseName;
        @JsonProperty
        private String judgementDate;
        @JsonProperty
        private Type type;

        @Getter
        public static class Type {
            @JsonProperty
            private String incident;
            @JsonProperty
            private String courtName;
            @JsonProperty
            private String verdict;
        }
    }

    @Getter
    public static class Relate {
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
}
