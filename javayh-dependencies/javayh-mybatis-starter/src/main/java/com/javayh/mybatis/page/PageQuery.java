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

import com.github.pagehelper.Page;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 分页工具类
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/4/4
 */
public class PageQuery<T> {

	/**
	 * 当前页码
	 */
	private Integer pageNum;

	/**
	 * 每页显示数量
	 */
	private Integer pageSize;

	/**
	 * 总条目数
	 */
	private Long totalCount;

	/**
	 * 总页数
	 */
	private Integer totalPage;

	private List<T> list;

	public PageQuery() {
	}

	public static <T> PageQuery<T> ofPage(List<T> list) {
		return new PageQuery<>(list);
	}

	private PageQuery(List<T> list) {
		this.list = list;
		if (list instanceof Page) {
			Page page = (Page) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.totalPage = page.getPages();
			this.totalCount = page.getTotal();
		}
		else {
			this.pageNum = 1;
			this.pageSize = ((Collection) list).size();
			this.totalPage = this.pageSize > 0 ? 1 : 0;
			this.totalCount = (long) ((Collection) list).size();
		}

	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
