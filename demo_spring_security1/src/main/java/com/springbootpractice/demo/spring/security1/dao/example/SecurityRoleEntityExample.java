package com.springbootpractice.demo.spring.security1.dao.example;

import java.util.ArrayList;
import java.util.List;

public class SecurityRoleEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public SecurityRoleEntityExample() {
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
        rows = null;
        offset = null;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public SecurityRoleEntityExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public SecurityRoleEntityExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public SecurityRoleEntityExample page(Integer page, Integer pageSize) {
        this.offset = (page - 1) * pageSize;
        this.rows = pageSize;
        return this;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Long value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Long value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Long value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Long value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Long value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Long value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Long> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Long> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Long value1, Long value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Long value1, Long value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameIsNull() {
            addCriterion("role_english_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameIsNotNull() {
            addCriterion("role_english_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameEqualTo(String value) {
            addCriterion("role_english_name =", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameNotEqualTo(String value) {
            addCriterion("role_english_name <>", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameGreaterThan(String value) {
            addCriterion("role_english_name >", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_english_name >=", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameLessThan(String value) {
            addCriterion("role_english_name <", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("role_english_name <=", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameLike(String value) {
            addCriterion("role_english_name like", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameNotLike(String value) {
            addCriterion("role_english_name not like", value, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameIn(List<String> values) {
            addCriterion("role_english_name in", values, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameNotIn(List<String> values) {
            addCriterion("role_english_name not in", values, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameBetween(String value1, String value2) {
            addCriterion("role_english_name between", value1, value2, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleEnglishNameNotBetween(String value1, String value2) {
            addCriterion("role_english_name not between", value1, value2, "roleEnglishName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameIsNull() {
            addCriterion("role_chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameIsNotNull() {
            addCriterion("role_chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameEqualTo(String value) {
            addCriterion("role_chinese_name =", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameNotEqualTo(String value) {
            addCriterion("role_chinese_name <>", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameGreaterThan(String value) {
            addCriterion("role_chinese_name >", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_chinese_name >=", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameLessThan(String value) {
            addCriterion("role_chinese_name <", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameLessThanOrEqualTo(String value) {
            addCriterion("role_chinese_name <=", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameLike(String value) {
            addCriterion("role_chinese_name like", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameNotLike(String value) {
            addCriterion("role_chinese_name not like", value, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameIn(List<String> values) {
            addCriterion("role_chinese_name in", values, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameNotIn(List<String> values) {
            addCriterion("role_chinese_name not in", values, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameBetween(String value1, String value2) {
            addCriterion("role_chinese_name between", value1, value2, "roleChineseName");
            return (Criteria) this;
        }

        public Criteria andRoleChineseNameNotBetween(String value1, String value2) {
            addCriterion("role_chinese_name not between", value1, value2, "roleChineseName");
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