package com.javayh.mybatis.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-04 22:41
 */
public class PageEntity {
    /** 起始页*/
	private int pageNo = 1;
    /**每页的条数*/
	private int pageSize;
	/**排序的字段*/
	private String orderBy;

    public PageEntity() {
    }

    public PageEntity(int pageNo, int pageSize, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        if(orderBy.contains("-")){
            orderBy=orderBy.replaceAll("-"," desc");

        }else if (orderBy.contains("+")){
            orderBy = orderBy.replaceAll("'+'", " asc");
        }
        this.orderBy = orderBy;
    }
}
