/**
 *   Copyright [2020] [Yang Hai Ji of copyright owner]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.javayh.mybatis.page;

import com.javayh.mybatis.uitl.Constant;
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
        if(orderBy.contains(Constant.MINUS_SIGN)){
            orderBy=orderBy.replaceAll(Constant.MINUS_SIGN, Constant.DESC);

        }
        if (orderBy.contains(Constant.PLUS)){
            orderBy = orderBy.replaceAll(Constant.PLUS_II, Constant.ASC);
        }
        this.orderBy = orderBy;
    }
}
