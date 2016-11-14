package com.symbolic;

import soot.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengyuan.ymy on 2016/11/11.
 */
public class ValueProperty {
    public Type trueType;
    public boolean hasTaint;
    public Object value;

    public Map<String, ValueProperty> fields = new HashMap();
}

