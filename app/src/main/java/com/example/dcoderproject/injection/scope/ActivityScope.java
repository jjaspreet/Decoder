package com.example.dcoderproject.injection.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

import dagger.MapKey;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}
