package com.tmt.toomanythoughts.commons.utils.scanning;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Component;

@Component
public class AnnotationAndAssignableTypeCandidateScanner {

	private final boolean useDefaultFilters = false;
	ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(this.useDefaultFilters);

	public Set<BeanDefinition> scan(final Class<? extends Annotation> annotationType, final Class<?> assignableType, final List<String> basePackages) {
		final Set<BeanDefinition> set = new HashSet<BeanDefinition>();
		this.scanner.resetFilters(this.useDefaultFilters);
		this.scanner.addIncludeFilter(new AnnotationAndAssignableTypeFilter(annotationType, assignableType));
		for (final String basePackage : basePackages) {
			set.addAll(this.scanner.findCandidateComponents(basePackage));
		}
		return set;
	}
}
