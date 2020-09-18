package com.offer.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PfoPaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PfoPaperExample() {
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

        public Criteria andPaperIdIsNull() {
            addCriterion("paper_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNotNull() {
            addCriterion("paper_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdEqualTo(Long value) {
            addCriterion("paper_id =", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotEqualTo(Long value) {
            addCriterion("paper_id <>", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThan(Long value) {
            addCriterion("paper_id >", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("paper_id >=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThan(Long value) {
            addCriterion("paper_id <", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThanOrEqualTo(Long value) {
            addCriterion("paper_id <=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIn(List<Long> values) {
            addCriterion("paper_id in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotIn(List<Long> values) {
            addCriterion("paper_id not in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdBetween(Long value1, Long value2) {
            addCriterion("paper_id between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotBetween(Long value1, Long value2) {
            addCriterion("paper_id not between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNull() {
            addCriterion("paper_name is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("paper_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("paper_name =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("paper_name <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("paper_name >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("paper_name >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("paper_name <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("paper_name <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("paper_name like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("paper_name not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("paper_name in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("paper_name not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("paper_name between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("paper_name not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("close_time is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("close_time is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("close_time =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("close_time <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("close_time >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("close_time >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("close_time <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("close_time <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("close_time in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("close_time not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("close_time between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("close_time not between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdIsNull() {
            addCriterion("question_list_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdIsNotNull() {
            addCriterion("question_list_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdEqualTo(String value) {
            addCriterion("question_list_id =", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdNotEqualTo(String value) {
            addCriterion("question_list_id <>", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdGreaterThan(String value) {
            addCriterion("question_list_id >", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdGreaterThanOrEqualTo(String value) {
            addCriterion("question_list_id >=", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdLessThan(String value) {
            addCriterion("question_list_id <", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdLessThanOrEqualTo(String value) {
            addCriterion("question_list_id <=", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdLike(String value) {
            addCriterion("question_list_id like", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdNotLike(String value) {
            addCriterion("question_list_id not like", value, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdIn(List<String> values) {
            addCriterion("question_list_id in", values, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdNotIn(List<String> values) {
            addCriterion("question_list_id not in", values, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdBetween(String value1, String value2) {
            addCriterion("question_list_id between", value1, value2, "questionListId");
            return (Criteria) this;
        }

        public Criteria andQuestionListIdNotBetween(String value1, String value2) {
            addCriterion("question_list_id not between", value1, value2, "questionListId");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionIsNull() {
            addCriterion("paper_description is null");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionIsNotNull() {
            addCriterion("paper_description is not null");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionEqualTo(String value) {
            addCriterion("paper_description =", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionNotEqualTo(String value) {
            addCriterion("paper_description <>", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionGreaterThan(String value) {
            addCriterion("paper_description >", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("paper_description >=", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionLessThan(String value) {
            addCriterion("paper_description <", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionLessThanOrEqualTo(String value) {
            addCriterion("paper_description <=", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionLike(String value) {
            addCriterion("paper_description like", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionNotLike(String value) {
            addCriterion("paper_description not like", value, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionIn(List<String> values) {
            addCriterion("paper_description in", values, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionNotIn(List<String> values) {
            addCriterion("paper_description not in", values, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionBetween(String value1, String value2) {
            addCriterion("paper_description between", value1, value2, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperDescriptionNotBetween(String value1, String value2) {
            addCriterion("paper_description not between", value1, value2, "paperDescription");
            return (Criteria) this;
        }

        public Criteria andPaperImgIsNull() {
            addCriterion("paper_img is null");
            return (Criteria) this;
        }

        public Criteria andPaperImgIsNotNull() {
            addCriterion("paper_img is not null");
            return (Criteria) this;
        }

        public Criteria andPaperImgEqualTo(String value) {
            addCriterion("paper_img =", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgNotEqualTo(String value) {
            addCriterion("paper_img <>", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgGreaterThan(String value) {
            addCriterion("paper_img >", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgGreaterThanOrEqualTo(String value) {
            addCriterion("paper_img >=", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgLessThan(String value) {
            addCriterion("paper_img <", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgLessThanOrEqualTo(String value) {
            addCriterion("paper_img <=", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgLike(String value) {
            addCriterion("paper_img like", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgNotLike(String value) {
            addCriterion("paper_img not like", value, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgIn(List<String> values) {
            addCriterion("paper_img in", values, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgNotIn(List<String> values) {
            addCriterion("paper_img not in", values, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgBetween(String value1, String value2) {
            addCriterion("paper_img between", value1, value2, "paperImg");
            return (Criteria) this;
        }

        public Criteria andPaperImgNotBetween(String value1, String value2) {
            addCriterion("paper_img not between", value1, value2, "paperImg");
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