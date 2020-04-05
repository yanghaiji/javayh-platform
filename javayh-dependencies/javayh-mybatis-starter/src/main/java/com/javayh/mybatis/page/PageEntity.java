package com.javayh.mybatis.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-04 22:41
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageEntity {

    private int pageNo = 1;
    private int pageSize;

}
