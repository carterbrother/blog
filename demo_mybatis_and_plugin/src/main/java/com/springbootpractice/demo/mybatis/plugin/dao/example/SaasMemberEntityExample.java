package com.springbootpractice.demo.mybatis.plugin.dao.example;

import com.springbootpractice.demo.mybatis.plugin.dao.entity.SaasMemberEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaasMemberEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public SaasMemberEntityExample() {
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

    public SaasMemberEntityExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public SaasMemberEntityExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public SaasMemberEntityExample page(Integer page, Integer pageSize) {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdIsNull() {
            addCriterion("saas_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdIsNotNull() {
            addCriterion("saas_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdEqualTo(Integer value) {
            addCriterion("saas_user_id =", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdNotEqualTo(Integer value) {
            addCriterion("saas_user_id <>", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdGreaterThan(Integer value) {
            addCriterion("saas_user_id >", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("saas_user_id >=", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdLessThan(Integer value) {
            addCriterion("saas_user_id <", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("saas_user_id <=", value, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdIn(List<Integer> values) {
            addCriterion("saas_user_id in", values, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdNotIn(List<Integer> values) {
            addCriterion("saas_user_id not in", values, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdBetween(Integer value1, Integer value2) {
            addCriterion("saas_user_id between", value1, value2, "saasUserId");
            return (Criteria) this;
        }

        public Criteria andSaasUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("saas_user_id not between", value1, value2, "saasUserId");
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

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdIsNull() {
            addCriterion("merchant_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdIsNotNull() {
            addCriterion("merchant_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdEqualTo(String value) {
            addCriterion("merchant_user_id =", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdNotEqualTo(String value) {
            addCriterion("merchant_user_id <>", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdGreaterThan(String value) {
            addCriterion("merchant_user_id >", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_user_id >=", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdLessThan(String value) {
            addCriterion("merchant_user_id <", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdLessThanOrEqualTo(String value) {
            addCriterion("merchant_user_id <=", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdLike(String value) {
            addCriterion("merchant_user_id like", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdNotLike(String value) {
            addCriterion("merchant_user_id not like", value, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdIn(List<String> values) {
            addCriterion("merchant_user_id in", values, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdNotIn(List<String> values) {
            addCriterion("merchant_user_id not in", values, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdBetween(String value1, String value2) {
            addCriterion("merchant_user_id between", value1, value2, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andMerchantUserIdNotBetween(String value1, String value2) {
            addCriterion("merchant_user_id not between", value1, value2, "merchantUserId");
            return (Criteria) this;
        }

        public Criteria andBecomeDescIsNull() {
            addCriterion("become_desc is null");
            return (Criteria) this;
        }

        public Criteria andBecomeDescIsNotNull() {
            addCriterion("become_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBecomeDescEqualTo(String value) {
            addCriterion("become_desc =", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescNotEqualTo(String value) {
            addCriterion("become_desc <>", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescGreaterThan(String value) {
            addCriterion("become_desc >", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescGreaterThanOrEqualTo(String value) {
            addCriterion("become_desc >=", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescLessThan(String value) {
            addCriterion("become_desc <", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescLessThanOrEqualTo(String value) {
            addCriterion("become_desc <=", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescLike(String value) {
            addCriterion("become_desc like", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescNotLike(String value) {
            addCriterion("become_desc not like", value, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescIn(List<String> values) {
            addCriterion("become_desc in", values, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescNotIn(List<String> values) {
            addCriterion("become_desc not in", values, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescBetween(String value1, String value2) {
            addCriterion("become_desc between", value1, value2, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andBecomeDescNotBetween(String value1, String value2) {
            addCriterion("become_desc not between", value1, value2, "becomeDesc");
            return (Criteria) this;
        }

        public Criteria andExpireAtIsNull() {
            addCriterion("expire_at is null");
            return (Criteria) this;
        }

        public Criteria andExpireAtIsNotNull() {
            addCriterion("expire_at is not null");
            return (Criteria) this;
        }

        public Criteria andExpireAtEqualTo(LocalDateTime value) {
            addCriterion("expire_at =", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtNotEqualTo(LocalDateTime value) {
            addCriterion("expire_at <>", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtGreaterThan(LocalDateTime value) {
            addCriterion("expire_at >", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("expire_at >=", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtLessThan(LocalDateTime value) {
            addCriterion("expire_at <", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("expire_at <=", value, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtIn(List<LocalDateTime> values) {
            addCriterion("expire_at in", values, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtNotIn(List<LocalDateTime> values) {
            addCriterion("expire_at not in", values, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expire_at between", value1, value2, "expireAt");
            return (Criteria) this;
        }

        public Criteria andExpireAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expire_at not between", value1, value2, "expireAt");
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

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andLogicalDeleted(boolean deleted) {
            return deleted ? andDeletedEqualTo(SaasMemberEntity.Deleted.IS_DELETED.value()) : andDeletedNotEqualTo(SaasMemberEntity.Deleted.IS_DELETED.value());
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