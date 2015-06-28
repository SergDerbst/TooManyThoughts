package com.tmt.sorcery.dataset.enhancer;

import javassist.Modifier;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

public class EnhancedEntity {

	private final Object entity;

	public EnhancedEntity (final Class<?> entityClass) {
		try {
			final ProxyFactory factory = new ProxyFactory();
			factory.setSuperclass(entityClass);
			factory.setFilter(method -> !Modifier.isStatic(method.getModifiers()) &&
			                  !Modifier.isAbstract(method.getModifiers()) &&
			                  !Modifier.isFinal(method.getModifiers()) &&
			                  Modifier.isPublic(method.getModifiers()) &&
			                  (method.getName().startsWith("get") || method.getName().startsWith("set")));
			final MethodHandler handler = (	self, thisMethod, proceed, args) -> self;
			final Class<?> proxiedClass = factory.createClass();
			final Object proxy = proxiedClass.newInstance();
			((Proxy) proxy).setHandler(handler);
			this.entity = proxy;
		} catch(final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
