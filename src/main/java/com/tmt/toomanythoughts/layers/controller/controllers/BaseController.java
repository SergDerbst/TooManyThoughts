package com.tmt.toomanythoughts.layers.controller.controllers;

import com.tmt.toomanythoughts.commons.annotations.controller.intercept.With;
import com.tmt.toomanythoughts.layers.controller.interceptors.LanguageInterceptor;

/**
 * Base controller essentially doing nothing, not even being a controller
 * (because the Spring annotations for controllers aren't inherited), but
 * defining some crucial interceptors at work.
 *
 * @author SergDerbst
 *
 */
@With(LanguageInterceptor.class)
public class BaseController {

}
