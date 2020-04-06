package com.javayh.swagger.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 * swagger
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-30 17:37
 */
public class SwaggerSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		return new String[] { "com.javayh.swagger.conf.SwaggerConfig" };
	}

}
