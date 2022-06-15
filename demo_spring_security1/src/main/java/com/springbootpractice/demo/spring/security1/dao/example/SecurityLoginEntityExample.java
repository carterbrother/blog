package com.springbootpractice.demo.spring.security1.dao.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SecurityLoginEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public SecurityLoginEntityExample() {
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

    public SecurityLoginEntityExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public SecurityLoginEntityExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public SecurityLoginEntityExample page(Integer page, Integer pageSize) {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateIsNull() {
            addCriterion("password_expire_date is null");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateIsNotNull() {
            addCriterion("password_expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateEqualTo(LocalDateTime value) {
            addCriterion("password_expire_date =", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateNotEqualTo(LocalDateTime value) {
            addCriterion("password_expire_date <>", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateGreaterThan(LocalDateTime value) {
            addCriterion("password_expire_date >", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("password_expire_date >=", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateLessThan(LocalDateTime value) {
            addCriterion("password_expire_date <", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("password_expire_date <=", value, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateIn(List<LocalDateTime> values) {
            addCriterion("password_expire_date in", values, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateNotIn(List<LocalDateTime> values) {
            addCriterion("password_expire_date not in", values, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("password_expire_date between", value1, value2, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andPasswordExpireDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("password_expire_date not between", value1, value2, "passwordExpireDate");
            return (Criteria) this;
        }

        public Criteria andLockFlagIsNull() {
            addCriterion("lock_flag is null");
            return (Criteria) this;
        }

        public Criteria andLockFlagIsNotNull() {
            addCriterion("lock_flag is not null");
            return (Criteria) this;
        }

        public Criteria andLockFlagEqualTo(Integer value) {
            addCriterion("lock_flag =", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagNotEqualTo(Integer value) {
            addCriterion("lock_flag <>", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagGreaterThan(Integer value) {
            addCriterion("lock_flag >", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("lock_flag >=", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagLessThan(Integer value) {
            addCriterion("lock_flag <", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagLessThanOrEqualTo(Integer value) {
            addCriterion("lock_flag <=", value, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagIn(List<Integer> values) {
            addCriterion("lock_flag in", values, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagNotIn(List<Integer> values) {
            addCriterion("lock_flag not in", values, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagBetween(Integer value1, Integer value2) {
            addCriterion("lock_flag between", value1, value2, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andLockFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("lock_flag not between", value1, value2, "lockFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagIsNull() {
            addCriterion("enable_flag is null");
            return (Criteria) this;
        }

        public Criteria andEnableFlagIsNotNull() {
            addCriterion("enable_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEnableFlagEqualTo(Integer value) {
            addCriterion("enable_flag =", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagNotEqualTo(Integer value) {
            addCriterion("enable_flag <>", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagGreaterThan(Integer value) {
            addCriterion("enable_flag >", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("enable_flag >=", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagLessThan(Integer value) {
            addCriterion("enable_flag <", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagLessThanOrEqualTo(Integer value) {
            addCriterion("enable_flag <=", value, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagIn(List<Integer> values) {
            addCriterion("enable_flag in", values, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagNotIn(List<Integer> values) {
            addCriterion("enable_flag not in", values, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagBetween(Integer value1, Integer value2) {
            addCriterion("enable_flag between", value1, value2, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andEnableFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("enable_flag not between", value1, value2, "enableFlag");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(LocalDateTime value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(LocalDateTime value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(LocalDateTime value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(LocalDateTime value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<LocalDateTime> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<LocalDateTime> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(LocalDateTime value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(LocalDateTime value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(LocalDateTime value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(LocalDateTime value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<LocalDateTime> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<LocalDateTime> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated not between", value1, value2, "updated");
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