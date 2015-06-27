package com.tmt.toomanythoughts.commons.utils.scanning;

import java.lang.annotation.Annotation;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationAndAssignableTypeFilter extends AnnotationTypeFilter {

	private final Class<?> assignableType;

	public AnnotationAndAssignableTypeFilter(final Class<? extends Annotation> annotationType,
			final Class<?> assignableType) {
		super(annotationType);
		this.assignableType = assignableType;
	}

	@Override
	protected boolean matchSelf(final MetadataReader metadataReader) {
		try {
			return super.matchSelf(metadataReader) && this.assignableType.isAssignableFrom(Class.forName(metadataReader.getClassMetadata().getClassName()));
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
