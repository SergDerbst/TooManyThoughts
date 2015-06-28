package com.tmt.sorcery.dataset.enhancer;

import javassist.Modifier;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

@SuppressWarnings("unchecked")
public class EntityEnhancer {

	public <E> E enhance(final Class<? extends E> entityClass) {
		try {
			final ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(entityClass);
			factory.setFilter(method -> !Modifier.isStatic(method.getModifiers()) &&
			                  !Modifier.isAbstract(method.getModifiers()) &&
			                  !Modifier.isFinal(method.getModifiers()) &&
			                  Modifier.isPublic(method.getModifiers()) &&
			                  (method.getName().startsWith("get") || method.getName().startsWith("set")));
			final MethodHandler handler = (	self,
					thisMethod,
					proceed,
					args) -> self;
					return (E) factory.create(new Class<?>[0], new Object[0], handler);
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
}