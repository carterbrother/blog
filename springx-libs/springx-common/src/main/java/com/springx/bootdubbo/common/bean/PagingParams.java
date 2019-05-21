package com.springx.bootdubbo.common.bean;

import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.BeanConverterUtil;
import com.springx.bootdubbo.common.util.CollectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 分页查询参数
 */
public class PagingParams implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE = 1;
    /**
     * 默认一页行数
     */
    public static final int DEFAULT_PAGESIZE = 10;
    /**
     * 第几页，从1开始
     */
    private Integer page = DEFAULT_PAGE;
    /**
     * 偏移量，从0开始，有此参数时忽略page
     */
    private Integer offset;
    /**
     * 加载多少条记录
     */
    private Integer pageSize = DEFAULT_PAGESIZE;
    /**
     * 排序字段名，多个以“,”隔开
     */
    private String sort;
    /**
     * 排序方式,asc/desc，多个以“,”隔开，与排序字段名一一匹配
     */
    private String order;
    /**
     * 数据库类型
     */
    private String dbType = "MySQL";
    /**
     * 排序字段映射
     */
    private Map<String, String> sortMapper;

    /**
     * @return 排序字段映射
     */
    public Map<String, String> getSortMapper() {
        return sortMapper;
    }

    /**
     * @param sortMapper 排序字段映射
     */
    public void setSortMapper(Map<String, String> sortMapper) {
        this.sortMapper = sortMapper;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * bean to map
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return BeanConverterUtil.beanToMap(this);
    }

    /**
     * 校验排序方式与排序方式是否匹配
     */
    public void validateOrderParams() {
        if (getSortList().size() != getOrderList().size()) {
            throw new BaseException(ErrorCodeMsgEnum.PARAM_FORMAT_ERROR.code(),
                    "The sort [" + sort + "] doesn't match the order [" + order + "]");
        }
    }

    /**
     * 校验排序字段
     *
     * @param allowedSortColumns 允许排序的字段名
     */
    public void validateSortParams(List<String> allowedSortColumns) {
        if (CollectionUtil.isEmpty(allowedSortColumns)) {
            throw new BaseException(ErrorCodeMsgEnum.PARAM_FORMAT_ERROR.code(),
                    "The query is not support sort.");
        }
        List<String> sortList = getSortList();
        if (CollectionUtil.isEmpty(sortList)) {
            return;
        }
        for (String sort : sortList) {
            if (!allowedSortColumns.contains(sort)) {
                throw new BaseException(ErrorCodeMsgEnum.PARAM_FORMAT_ERROR.code(),
                        "The sort [" + sort + "] is not allowed.");
            }
        }

    }

    /**
     * 排序字段列表
     *
     * @return
     */
    public List<String> getSortList() {
        if (StringUtils.isEmpty(sort)) {
            return new ArrayList<>();
        }
        return Arrays.asList(sort.split(","));
    }

    /**
     * 排序方式列表
     *
     * @return
     */
    public List<String> getOrderList() {
        if (StringUtils.isEmpty(order)) {
            return new ArrayList<>();
        }
        return Arrays.asList(order.split(","));
    }


    /**
     * @return 偏移量，有此参数时忽略page
     */
    public Integer getOffset() {
        if (this.offset != null) {
            return this.offset;
        }
        return this.getPage() != null && this.getPage() > 0 && this.getPageSize() != null
                && this.getPageSize() > 0 ? (this.getPage() - 1) * this.getPageSize() : 0;
    }

    /**
     * @param offset 偏移量，有此参数时忽略page
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * 排序字段名，多个以“,”隔开
     *
     * @return
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * @param sort 排序字段名，多个以“,”隔开
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 排序方式,asc/desc，多个以“,”隔开
     *
     * @return
     */
    public String getOrder() {
        return this.order;
    }

    /**
     * @param order 排序方式,asc/desc，多个以“,”隔开
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 第几页，从1开始
     *
     * @return
     */
    public Integer getPage() {
        return this.page;
    }

    /**
     * @param page 第几页，从1开始
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return 每页多少条记录
     */
    public Integer getPageSize() {
        // 兼容rows传参方式
        return this.pageSize == null ? 0 : this.pageSize;
    }

    /**
     * @param pageSize 每页多少条记录
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取排序条件,例：column1 asc,column2 desc
     *
     * @return
     */
    public String getMySqlOrderCondition() {
        List<String> sortList = this.getSortList();
        List<String> orderList = this.getOrderList();
        if (CollectionUtil.isEmpty(sortList) || CollectionUtil.isEmpty(orderList)) {
            return null;
        }
        StringBuilder orderby = new StringBuilder();
        int size = sortList.size();
        for (int i = 0; i < size; i++) {
            String sortKey = sortList.get(i);
            if (sortMapper != null && sortMapper.containsKey(sortKey)) {
                sortKey = sortMapper.get(sortKey);
            }
            orderby.append(sortKey);
            orderby.append(" ");
            orderby.append(orderList.get(i));
            if (i != size - 1) {
                orderby.append(",");
            }
        }
        return orderby.toString();
    }

    /**
     * 获取排序条件,例：column1 asc,column2 desc
     *
     * @param sortMapper 排序字段映射
     * @return
     */
    public String getMySqlOrderCondition(Map<String, String> sortMapper) {
        validateOrderParams();
        List<String> sortList = this.getSortList();
        List<String> orderList = this.getOrderList();
        if (CollectionUtil.isEmpty(sortList) || CollectionUtil.isEmpty(orderList)) {
            return null;
        }
        StringBuilder orderby = new StringBuilder();
        int size = sortList.size();
        for (int i = 0; i < size; i++) {
            String sortKey = sortList.get(i);
            if (sortMapper != null && sortMapper.containsKey(sortKey)) {
                sortKey = sortMapper.get(sortKey);
            }
            orderby.append(sortKey);
            orderby.append(" ");
            orderby.append(orderList.get(i));
            if (i != size - 1) {
                orderby.append(",");
            }
        }
        return orderby.toString();
    }

    /**
     * 处理获取排序条件,例：column1 asc,column2 desc
     * <p>
     * <b>目前仅支持mysql</b>
     *
     * @return
     */
    public String getOrderBy() {
        if (this.dbType == null) {
            return getMySqlOrderCondition();
        }
        switch (this.dbType) {
            case "MySQL":
                return getMySqlOrderCondition();
            default:
                return getMySqlOrderCondition();
        }
    }
}