package com.mc0239.recyclertableview.annotation;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RecyclerTableColumn {
    /**
     * @return View ID the field should be mapped to.
     */
    @IdRes int value();
}
