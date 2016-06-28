package net.vsona.orz;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by roy on 16/6/24.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {}
