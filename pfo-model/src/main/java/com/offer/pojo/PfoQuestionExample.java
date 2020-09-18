package com.offer.pojo;

import java.util.ArrayList;
import java.util.List;

public class PfoQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PfoQuestionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(Long value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(Long value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(Long value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(Long value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(Long value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<Long> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<Long> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(Long value1, Long value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(Long value1, Long value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIsNull() {
            addCriterion("question_content is null");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIsNotNull() {
            addCriterion("question_content is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionContentEqualTo(String value) {
            addCriterion("question_content =", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotEqualTo(String value) {
            addCriterion("question_content <>", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentGreaterThan(String value) {
            addCriterion("question_content >", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentGreaterThanOrEqualTo(String value) {
            addCriterion("question_content >=", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLessThan(String value) {
            addCriterion("question_content <", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLessThanOrEqualTo(String value) {
            addCriterion("question_content <=", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentLike(String value) {
            addCriterion("question_content like", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotLike(String value) {
            addCriterion("question_content not like", value, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentIn(List<String> values) {
            addCriterion("question_content in", values, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotIn(List<String> values) {
            addCriterion("question_content not in", values, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentBetween(String value1, String value2) {
            addCriterion("question_content between", value1, value2, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionContentNotBetween(String value1, String value2) {
            addCriterion("question_content not between", value1, value2, "questionContent");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNull() {
            addCriterion("question_type is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNotNull() {
            addCriterion("question_type is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeEqualTo(String value) {
            addCriterion("question_type =", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotEqualTo(String value) {
            addCriterion("question_type <>", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThan(String value) {
            addCriterion("question_type >", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("question_type >=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThan(String value) {
            addCriterion("question_type <", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThanOrEqualTo(String value) {
            addCriterion("question_type <=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLike(String value) {
            addCriterion("question_type like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotLike(String value) {
            addCriterion("question_type not like", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIn(List<String> values) {
            addCriterion("question_type in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotIn(List<String> values) {
            addCriterion("question_type not in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeBetween(String value1, String value2) {
            addCriterion("question_type between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotBetween(String value1, String value2) {
            addCriterion("question_type not between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectIsNull() {
            addCriterion("question_subject is null");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectIsNotNull() {
            addCriterion("question_subject is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectEqualTo(String value) {
            addCriterion("question_subject =", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectNotEqualTo(String value) {
            addCriterion("question_subject <>", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectGreaterThan(String value) {
            addCriterion("question_subject >", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("question_subject >=", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectLessThan(String value) {
            addCriterion("question_subject <", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectLessThanOrEqualTo(String value) {
            addCriterion("question_subject <=", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectLike(String value) {
            addCriterion("question_subject like", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectNotLike(String value) {
            addCriterion("question_subject not like", value, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectIn(List<String> values) {
            addCriterion("question_subject in", values, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectNotIn(List<String> values) {
            addCriterion("question_subject not in", values, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectBetween(String value1, String value2) {
            addCriterion("question_subject between", value1, value2, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionSubjectNotBetween(String value1, String value2) {
            addCriterion("question_subject not between", value1, value2, "questionSubject");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelIsNull() {
            addCriterion("question_level is null");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelIsNotNull() {
            addCriterion("question_level is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelEqualTo(String value) {
            addCriterion("question_level =", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelNotEqualTo(String value) {
            addCriterion("question_level <>", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelGreaterThan(String value) {
            addCriterion("question_level >", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelGreaterThanOrEqualTo(String value) {
            addCriterion("question_level >=", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelLessThan(String value) {
            addCriterion("question_level <", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelLessThanOrEqualTo(String value) {
            addCriterion("question_level <=", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelLike(String value) {
            addCriterion("question_level like", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelNotLike(String value) {
            addCriterion("question_level not like", value, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelIn(List<String> values) {
            addCriterion("question_level in", values, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelNotIn(List<String> values) {
            addCriterion("question_level not in", values, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelBetween(String value1, String value2) {
            addCriterion("question_level between", value1, value2, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andQuestionLevelNotBetween(String value1, String value2) {
            addCriterion("question_level not between", value1, value2, "questionLevel");
            return (Criteria) this;
        }

        public Criteria andStandardTimeIsNull() {
            addCriterion("standard_time is null");
            return (Criteria) this;
        }

        public Criteria andStandardTimeIsNotNull() {
            addCriterion("standard_time is not null");
            return (Criteria) this;
        }

        public Criteria andStandardTimeEqualTo(Integer value) {
            addCriterion("standard_time =", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeNotEqualTo(Integer value) {
            addCriterion("standard_time <>", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeGreaterThan(Integer value) {
            addCriterion("standard_time >", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("standard_time >=", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeLessThan(Integer value) {
            addCriterion("standard_time <", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeLessThanOrEqualTo(Integer value) {
            addCriterion("standard_time <=", value, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeIn(List<Integer> values) {
            addCriterion("standard_time in", values, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeNotIn(List<Integer> values) {
            addCriterion("standard_time not in", values, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeBetween(Integer value1, Integer value2) {
            addCriterion("standard_time between", value1, value2, "standardTime");
            return (Criteria) this;
        }

        public Criteria andStandardTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("standard_time not between", value1, value2, "standardTime");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisIsNull() {
            addCriterion("answer_analysis is null");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisIsNotNull() {
            addCriterion("answer_analysis is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisEqualTo(String value) {
            addCriterion("answer_analysis =", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisNotEqualTo(String value) {
            addCriterion("answer_analysis <>", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisGreaterThan(String value) {
            addCriterion("answer_analysis >", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisGreaterThanOrEqualTo(String value) {
            addCriterion("answer_analysis >=", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisLessThan(String value) {
            addCriterion("answer_analysis <", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisLessThanOrEqualTo(String value) {
            addCriterion("answer_analysis <=", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisLike(String value) {
            addCriterion("answer_analysis like", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisNotLike(String value) {
            addCriterion("answer_analysis not like", value, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisIn(List<String> values) {
            addCriterion("answer_analysis in", values, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisNotIn(List<String> values) {
            addCriterion("answer_analysis not in", values, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisBetween(String value1, String value2) {
            addCriterion("answer_analysis between", value1, value2, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerAnalysisNotBetween(String value1, String value2) {
            addCriterion("answer_analysis not between", value1, value2, "answerAnalysis");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteIsNull() {
            addCriterion("answer_note is null");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteIsNotNull() {
            addCriterion("answer_note is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteEqualTo(String value) {
            addCriterion("answer_note =", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteNotEqualTo(String value) {
            addCriterion("answer_note <>", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteGreaterThan(String value) {
            addCriterion("answer_note >", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteGreaterThanOrEqualTo(String value) {
            addCriterion("answer_note >=", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteLessThan(String value) {
            addCriterion("answer_note <", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteLessThanOrEqualTo(String value) {
            addCriterion("answer_note <=", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteLike(String value) {
            addCriterion("answer_note like", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteNotLike(String value) {
            addCriterion("answer_note not like", value, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteIn(List<String> values) {
            addCriterion("answer_note in", values, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteNotIn(List<String> values) {
            addCriterion("answer_note not in", values, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteBetween(String value1, String value2) {
            addCriterion("answer_note between", value1, value2, "answerNote");
            return (Criteria) this;
        }

        public Criteria andAnswerNoteNotBetween(String value1, String value2) {
            addCriterion("answer_note not between", value1, value2, "answerNote");
            return (Criteria) this;
        }

        public Criteria andWeighIsNull() {
            addCriterion("weigh is null");
            return (Criteria) this;
        }

        public Criteria andWeighIsNotNull() {
            addCriterion("weigh is not null");
            return (Criteria) this;
        }

        public Criteria andWeighEqualTo(Integer value) {
            addCriterion("weigh =", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighNotEqualTo(Integer value) {
            addCriterion("weigh <>", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighGreaterThan(Integer value) {
            addCriterion("weigh >", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighGreaterThanOrEqualTo(Integer value) {
            addCriterion("weigh >=", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighLessThan(Integer value) {
            addCriterion("weigh <", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighLessThanOrEqualTo(Integer value) {
            addCriterion("weigh <=", value, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighIn(List<Integer> values) {
            addCriterion("weigh in", values, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighNotIn(List<Integer> values) {
            addCriterion("weigh not in", values, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighBetween(Integer value1, Integer value2) {
            addCriterion("weigh between", value1, value2, "weigh");
            return (Criteria) this;
        }

        public Criteria andWeighNotBetween(Integer value1, Integer value2) {
            addCriterion("weigh not between", value1, value2, "weigh");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}