package com.javayh.log.entity;

import java.util.List;

/**
 * <p>
 * 日志记录
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-16 22:05
 */
public record LogInfoEntity(List<OperationLog> log) {
}
