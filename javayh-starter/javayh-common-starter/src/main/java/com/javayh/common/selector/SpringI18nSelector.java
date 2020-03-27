package com.javayh.common.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 *      实例化
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-03 10:53
 */
public class SpringI18nSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "com.javayh.common.i18n.config.InternationalConfig",
                "com.javayh.common.i18n.I18nResponseBodyAdvice"
        };
    }
}
