package com.offer.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PfoRecruitmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PfoRecruitmentExample() {
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

        public Criteria andRecruitmentIdIsNull() {
            addCriterion("recruitment_id is null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdIsNotNull() {
            addCriterion("recruitment_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdEqualTo(Long value) {
            addCriterion("recruitment_id =", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdNotEqualTo(Long value) {
            addCriterion("recruitment_id <>", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdGreaterThan(Long value) {
            addCriterion("recruitment_id >", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recruitment_id >=", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdLessThan(Long value) {
            addCriterion("recruitment_id <", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdLessThanOrEqualTo(Long value) {
            addCriterion("recruitment_id <=", value, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdIn(List<Long> values) {
            addCriterion("recruitment_id in", values, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdNotIn(List<Long> values) {
            addCriterion("recruitment_id not in", values, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdBetween(Long value1, Long value2) {
            addCriterion("recruitment_id between", value1, value2, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIdNotBetween(Long value1, Long value2) {
            addCriterion("recruitment_id not between", value1, value2, "recruitmentId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleIsNull() {
            addCriterion("recruitment_title is null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleIsNotNull() {
            addCriterion("recruitment_title is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleEqualTo(String value) {
            addCriterion("recruitment_title =", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleNotEqualTo(String value) {
            addCriterion("recruitment_title <>", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleGreaterThan(String value) {
            addCriterion("recruitment_title >", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleGreaterThanOrEqualTo(String value) {
            addCriterion("recruitment_title >=", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleLessThan(String value) {
            addCriterion("recruitment_title <", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleLessThanOrEqualTo(String value) {
            addCriterion("recruitment_title <=", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleLike(String value) {
            addCriterion("recruitment_title like", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleNotLike(String value) {
            addCriterion("recruitment_title not like", value, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleIn(List<String> values) {
            addCriterion("recruitment_title in", values, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleNotIn(List<String> values) {
            addCriterion("recruitment_title not in", values, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleBetween(String value1, String value2) {
            addCriterion("recruitment_title between", value1, value2, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitmentTitleNotBetween(String value1, String value2) {
            addCriterion("recruitment_title not between", value1, value2, "recruitmentTitle");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNull() {
            addCriterion("deliver_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNotNull() {
            addCriterion("deliver_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeEqualTo(Date value) {
            addCriterion("deliver_time =", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotEqualTo(Date value) {
            addCriterion("deliver_time <>", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThan(Date value) {
            addCriterion("deliver_time >", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_time >=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThan(Date value) {
            addCriterion("deliver_time <", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliver_time <=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIn(List<Date> values) {
            addCriterion("deliver_time in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotIn(List<Date> values) {
            addCriterion("deliver_time not in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeBetween(Date value1, Date value2) {
            addCriterion("deliver_time between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliver_time not between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgIsNull() {
            addCriterion("recruitment_img is null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgIsNotNull() {
            addCriterion("recruitment_img is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgEqualTo(String value) {
            addCriterion("recruitment_img =", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgNotEqualTo(String value) {
            addCriterion("recruitment_img <>", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgGreaterThan(String value) {
            addCriterion("recruitment_img >", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgGreaterThanOrEqualTo(String value) {
            addCriterion("recruitment_img >=", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgLessThan(String value) {
            addCriterion("recruitment_img <", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgLessThanOrEqualTo(String value) {
            addCriterion("recruitment_img <=", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgLike(String value) {
            addCriterion("recruitment_img like", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgNotLike(String value) {
            addCriterion("recruitment_img not like", value, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgIn(List<String> values) {
            addCriterion("recruitment_img in", values, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgNotIn(List<String> values) {
            addCriterion("recruitment_img not in", values, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgBetween(String value1, String value2) {
            addCriterion("recruitment_img between", value1, value2, "recruitmentImg");
            return (Criteria) this;
        }

        public Criteria andRecruitmentImgNotBetween(String value1, String value2) {
            addCriterion("recruitment_img not between", value1, value2, "recruitmentImg");
            return (Criteria) this;
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