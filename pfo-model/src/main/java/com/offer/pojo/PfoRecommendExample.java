package com.offer.pojo;

import java.util.ArrayList;
import java.util.List;

public class PfoRecommendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PfoRecommendExample() {
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

        public Criteria andRecommendIdIsNull() {
            addCriterion("recommend_id is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIsNotNull() {
            addCriterion("recommend_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendIdEqualTo(Integer value) {
            addCriterion("recommend_id =", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotEqualTo(Integer value) {
            addCriterion("recommend_id <>", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThan(Integer value) {
            addCriterion("recommend_id >", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend_id >=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThan(Integer value) {
            addCriterion("recommend_id <", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdLessThanOrEqualTo(Integer value) {
            addCriterion("recommend_id <=", value, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdIn(List<Integer> values) {
            addCriterion("recommend_id in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotIn(List<Integer> values) {
            addCriterion("recommend_id not in", values, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdBetween(Integer value1, Integer value2) {
            addCriterion("recommend_id between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andRecommendIdNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend_id not between", value1, value2, "recommendId");
            return (Criteria) this;
        }

        public Criteria andPaperIdListIsNull() {
            addCriterion("paper_id_list is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdListIsNotNull() {
            addCriterion("paper_id_list is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdListEqualTo(String value) {
            addCriterion("paper_id_list =", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListNotEqualTo(String value) {
            addCriterion("paper_id_list <>", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListGreaterThan(String value) {
            addCriterion("paper_id_list >", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListGreaterThanOrEqualTo(String value) {
            addCriterion("paper_id_list >=", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListLessThan(String value) {
            addCriterion("paper_id_list <", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListLessThanOrEqualTo(String value) {
            addCriterion("paper_id_list <=", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListLike(String value) {
            addCriterion("paper_id_list like", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListNotLike(String value) {
            addCriterion("paper_id_list not like", value, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListIn(List<String> values) {
            addCriterion("paper_id_list in", values, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListNotIn(List<String> values) {
            addCriterion("paper_id_list not in", values, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListBetween(String value1, String value2) {
            addCriterion("paper_id_list between", value1, value2, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPaperIdListNotBetween(String value1, String value2) {
            addCriterion("paper_id_list not between", value1, value2, "paperIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListIsNull() {
            addCriterion("post_id_list is null");
            return (Criteria) this;
        }

        public Criteria andPostIdListIsNotNull() {
            addCriterion("post_id_list is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdListEqualTo(String value) {
            addCriterion("post_id_list =", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListNotEqualTo(String value) {
            addCriterion("post_id_list <>", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListGreaterThan(String value) {
            addCriterion("post_id_list >", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListGreaterThanOrEqualTo(String value) {
            addCriterion("post_id_list >=", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListLessThan(String value) {
            addCriterion("post_id_list <", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListLessThanOrEqualTo(String value) {
            addCriterion("post_id_list <=", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListLike(String value) {
            addCriterion("post_id_list like", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListNotLike(String value) {
            addCriterion("post_id_list not like", value, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListIn(List<String> values) {
            addCriterion("post_id_list in", values, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListNotIn(List<String> values) {
            addCriterion("post_id_list not in", values, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListBetween(String value1, String value2) {
            addCriterion("post_id_list between", value1, value2, "postIdList");
            return (Criteria) this;
        }

        public Criteria andPostIdListNotBetween(String value1, String value2) {
            addCriterion("post_id_list not between", value1, value2, "postIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListIsNull() {
            addCriterion("recruitment_id_list is null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListIsNotNull() {
            addCriterion("recruitment_id_list is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListEqualTo(String value) {
            addCriterion("recruitment_id_list =", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListNotEqualTo(String value) {
            addCriterion("recruitment_id_list <>", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListGreaterThan(String value) {
            addCriterion("recruitment_id_list >", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListGreaterThanOrEqualTo(String value) {
            addCriterion("recruitment_id_list >=", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListLessThan(String value) {
            addCriterion("recruitment_id_list <", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListLessThanOrEqualTo(String value) {
            addCriterion("recruitment_id_list <=", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListLike(String value) {
            addCriterion("recruitment_id_list like", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListNotLike(String value) {
            addCriterion("recruitment_id_list not like", value, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListIn(List<String> values) {
            addCriterion("recruitment_id_list in", values, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListNotIn(List<String> values) {
            addCriterion("recruitment_id_list not in", values, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListBetween(String value1, String value2) {
            addCriterion("recruitment_id_list between", value1, value2, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdListNotBetween(String value1, String value2) {
            addCriterion("recruitment_id_list not between", value1, value2, "recruitmentIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListIsNull() {
            addCriterion("news_id_list is null");
            return (Criteria) this;
        }

        public Criteria andNewsIdListIsNotNull() {
            addCriterion("news_id_list is not null");
            return (Criteria) this;
        }

        public Criteria andNewsIdListEqualTo(String value) {
            addCriterion("news_id_list =", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListNotEqualTo(String value) {
            addCriterion("news_id_list <>", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListGreaterThan(String value) {
            addCriterion("news_id_list >", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListGreaterThanOrEqualTo(String value) {
            addCriterion("news_id_list >=", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListLessThan(String value) {
            addCriterion("news_id_list <", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListLessThanOrEqualTo(String value) {
            addCriterion("news_id_list <=", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListLike(String value) {
            addCriterion("news_id_list like", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListNotLike(String value) {
            addCriterion("news_id_list not like", value, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListIn(List<String> values) {
            addCriterion("news_id_list in", values, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListNotIn(List<String> values) {
            addCriterion("news_id_list not in", values, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListBetween(String value1, String value2) {
            addCriterion("news_id_list between", value1, value2, "newsIdList");
            return (Criteria) this;
        }

        public Criteria andNewsIdListNotBetween(String value1, String value2) {
            addCriterion("news_id_list not between", value1, value2, "newsIdList");
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