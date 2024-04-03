package site.pangarm.backend.application.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class SearchPrecedentClientResponse {
    @JsonProperty("data")
    private List<Precedent> precedentList;

    @Getter
    public static class Precedent {
        @JsonProperty
        private Info info;
        @JsonProperty
        private double score;
        @JsonProperty
        private String summary;
        @JsonProperty
        private String judgement;
        @JsonProperty
        private String conclusion;
        @JsonProperty
        private List<String> keywordList;

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

            public LocalDate getJudgementDate() {
                StringTokenizer st = new StringTokenizer(judgementDate, ".");
                return LocalDate.of(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            @Getter
            public static class Type {
                @JsonProperty
                private String incident;
                @JsonProperty
                private String courtName;
                @JsonProperty
                private String verdict;
            }

            public String getCaseTypeName() {
                Pattern pattern = Pattern.compile("(\\d+)(\\D+)(\\d+)");
                Matcher matcher = pattern.matcher(caseNumber);

                if (matcher.find()) {
                    int caseYear = Integer.parseInt(matcher.group(1));
                    String caseTypeName = matcher.group(2);
                    int registrationNumber = Integer.parseInt(matcher.group(3));

                    return caseTypeName;
                }
                return "";
            }
        }
    }
}
