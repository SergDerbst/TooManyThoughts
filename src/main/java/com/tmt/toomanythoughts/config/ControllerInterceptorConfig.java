package com.tmt.toomanythoughts.config;

import static com.tmt.toomanythoughts.commons.utils.reflection.ClassReflectionUtils.retrieveActiveAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tmt.toomanythoughts.App;
import com.tmt.toomanythoughts.commons.annotations.controller.intercept.With;
import com.tmt.toomanythoughts.commons.exceptions.controller.impl.CandidateScanFailedException;
import com.tmt.toomanythoughts.commons.utils.scanning.AnnotationCandidateScanner;

/**
 * <p>
 * Currently the Spring framework doesn't allow for configuring
 * {@link HandlerInterceptor}s with annotations. Since you cannot mix annotation
 * driven and XML configuration in Spring, we need to extend the capability of
 * the framework by introducing the repeatable annotation {@link With} available
 * on both controller classes and methods.
 * </p>
 * <p>
 * The interceptor configurator scans the classpath starting with the
 * {@link App} class's package as base package, so it works just as the common
 * Spring Boot configuration. It collects all candidates annotated with
 * {@link Controller} or {@link RestController}. It then collects all
 * {@link With}s on class level and adds an instance of the annotated
 * {@link HandlerInterceptor} class to the {@link InterceptorRegistry}. The
 * according {@link InterceptorRegistration} is then configured with all
 * {@link RequestMapping}s contained on both class and method level. </br> The
 * same is then done for any {@link With} on method level, whereas request
 * mappings on class level will be considered, yet only the request mapping on
 * method level if there exists one for the particular method. This allows for
 * proper interception on single controller methods.
 * </p>
 *
 * @author Sergio Weigel
 *
 */
@Configuration
public class ControllerInterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	AnnotationCandidateScanner	candidateScanner;

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		try {
			final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap = new HashMap<>();
			this.handleControllers(RestController.class, registry, interceptorPatternMap);
			this.addInterceptorsToHandlerMapping(registry, interceptorPatternMap);
			super.addInterceptors(registry);
		} catch (final Exception e) {
			throw new CandidateScanFailedException(e);
		}
	}

	private void handleControllers(	final Class<? extends Annotation> annotationType,
																	final InterceptorRegistry registry,
																	final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) throws ClassNotFoundException, LinkageError, Exception {
		for (final BeanDefinition beanDefinition : this.candidateScanner.scan(annotationType, App.class.getPackage()
																																																		.getName())) {
			final Class<?> controllerClass = ClassUtils.forName(beanDefinition.getBeanClassName(), ClassUtils.getDefaultClassLoader());
			this.handleInterceptorsOnClassLevel(controllerClass, interceptorPatternMap);
			this.handleInterceptorsOnMethodLevel(controllerClass, interceptorPatternMap);
		}
	}

	private void handleInterceptorsOnClassLevel(final Class<?> controllerClass,
																							final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		for (final Annotation annotation : retrieveActiveAnnotations(controllerClass)) {
			if (With.class.equals(annotation.annotationType())) {
				this.handleClassInterceptorWithRequestMappingOnClassLevel((With) annotation, controllerClass, interceptorPatternMap);
				this.handleClassInterceptorWithRequestMappingOnMethodLevel((With) annotation, controllerClass, interceptorPatternMap);
			}
		}
	}

	private void handleInterceptorsOnMethodLevel(	final Class<?> controllerClass,
																								final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		for (final Method method : controllerClass.getMethods()) {
			for (final Annotation annotation : method.getAnnotations()) {
				if (With.class.equals(annotation.getClass())) {
					this.handleMethodInterceptorWithRequestMappingOnClassLevel((With) annotation, controllerClass, interceptorPatternMap);
					this.handleMethodInterceptorWithRequestMappingOnMethodLevel((With) annotation, method, interceptorPatternMap);
				}
			}
		}
	}

	private void handleMethodInterceptorWithRequestMappingOnMethodLevel(final With intercept,
																																			final Method method,
																																			final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		final RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		if (requestMapping != null) {
			this.addInterception(intercept.value(), requestMapping.value(), interceptorPatternMap);
		}
	}

	private void handleMethodInterceptorWithRequestMappingOnClassLevel(	final With intercept,
																																			final Class<?> controllerClass,
																																			final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		final RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);
		if (requestMapping != null) {
			this.addInterception(intercept.value(), requestMapping.value(), interceptorPatternMap);
		}
	}

	private void handleClassInterceptorWithRequestMappingOnMethodLevel(	final With intercept,
																																			final Class<?> controllerClass,
																																			final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		System.out.println("interceptor on class level request mapping on method level");
		for (final Method method : controllerClass.getMethods()) {
			final RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (requestMapping != null) {
				this.addInterception(intercept.value(), requestMapping.value(), interceptorPatternMap);
			}
		}
	}

	private void handleClassInterceptorWithRequestMappingOnClassLevel(final With intercept,
																																		final Class<?> controllerClass,
																																		final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		final RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);
		if (requestMapping != null) {
			this.addInterception(intercept.value(), requestMapping.value(), interceptorPatternMap);
		}
	}

	private void addInterception(	final Class<? extends HandlerInterceptor> interceptorClass,
																final String[] requestMappings,
																final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) {
		List<String> interceptedRequestPatterns = interceptorPatternMap.get(interceptorClass);
		if (interceptedRequestPatterns == null) {
			interceptedRequestPatterns = new ArrayList<String>();
		}
		for (final String requestMapping : requestMappings) {
			if (requestMapping.isEmpty()) {
				continue;
			}
			if (!interceptedRequestPatterns.contains(requestMapping)) {
				interceptedRequestPatterns.add(requestMapping);
			}
		}
		interceptorPatternMap.put(interceptorClass, interceptedRequestPatterns);
	}

	private void addInterceptorsToHandlerMapping(	final InterceptorRegistry registry,
																								final Map<Class<? extends HandlerInterceptor>, List<String>> interceptorPatternMap) throws Exception {
		for (final Entry<Class<? extends HandlerInterceptor>, List<String>> entry : interceptorPatternMap.entrySet()) {
			final InterceptorRegistration interceptorRegistration = registry.addInterceptor(entry.getKey()
																																														.newInstance());
			for (final String pattern : entry.getValue()) {
				interceptorRegistration.addPathPatterns(pattern);
			}
		}
	}
}
