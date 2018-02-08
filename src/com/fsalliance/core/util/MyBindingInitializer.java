package com.fsalliance.core.util;

import java.beans.PropertyEditorSupport;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyBindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(List.class, new PropertyEditorSupport(){
			@SuppressWarnings({ "unchecked", "deprecation" })
			public void setAsText(String text) throws IllegalArgumentException {
				//GenericConverter	
				JSONArray jsonArray = JSONArray.fromObject(text);
				List ls = JSONArray.toList(jsonArray);
				setValue(ls);
			}
		});
	}
}
