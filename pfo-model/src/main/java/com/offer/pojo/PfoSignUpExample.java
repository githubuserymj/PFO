package com.offer.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PfoSignUpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PfoSignUpExample() {
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

        public Criteria andSignUpIdIsNull() {
            addCriterion("sign_up_id is null");
            return (Criteria) this;
        }

        public Criteria andSignUpIdIsNotNull() {
            addCriterion("sign_up_id is not null");
            return (Criteria) this;
        }

        public Criteria andSignUpIdEqualTo(Long value) {
            addCriterion("sign_up_id =", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdNotEqualTo(Long value) {
            addCriterion("sign_up_id <>", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdGreaterThan(Long value) {
            addCriterion("sign_up_id >", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sign_up_id >=", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdLessThan(Long value) {
            addCriterion("sign_up_id <", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdLessThanOrEqualTo(Long value) {
            addCriterion("sign_up_id <=", value, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdIn(List<Long> values) {
            addCriterion("sign_up_id in", values, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdNotIn(List<Long> values) {
            addCriterion("sign_up_id not in", values, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdBetween(Long value1, Long value2) {
            addCriterion("sign_up_id between", value1, value2, "signUpId");
            return (Criteria) this;
        }

        public Criteria andSignUpIdNotBetween(Long value1, Long value2) {
            addCriterion("sign_up_id not between", value1, value2, "signUpId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
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