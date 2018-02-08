package com.fsalliance.core.util;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.fsalliance.core.vo.CLS_VO_Base;

public class MyGenericConverter implements GenericConverter {

	public Object convert(Object arg0, TypeDescriptor arg1, TypeDescriptor arg2) {
		if (arg1.getType() != String.class) {
			return arg0;
		}
		Class<?> c2 = arg2.getType();
		if (c2.isArray()) {
			JSONArray jsonArray = JSONArray.fromObject(arg0);
			Object obj = JSONArray.toArray(jsonArray, c2.getComponentType());
			return obj;
		} else {
			JSONObject jsonObject = JSONObject.fromObject(arg0);
			Object obj = JSONObject.toBean(jsonObject, c2);
			return obj;
		}
	}

	public Set<ConvertiblePair> getConvertibleTypes() {
		
		Set<ConvertiblePair> set = new HashSet<ConvertiblePair>();
		set.add(new ConvertiblePair(String.class, CLS_VO_Base.class));
		set.add(new ConvertiblePair(String.class, CLS_VO_Base[].class));
		set.add(new ConvertiblePair(String.class, String[].class));
		set.add(new ConvertiblePair(String.class, int[].class));
		set.add(new ConvertiblePair(String.class, double[].class));
		return set;
	}
}
