package com.rodrigo.hospitaliza.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.rodrigo.hospitaliza.enums.FuncaoEnum;

@FacesConverter(forClass = FuncaoEnum.class)
@Named
public class FuncaoEnumConverter implements Converter<FuncaoEnum> {

	@Override
	public FuncaoEnum getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) return null;
		return FuncaoEnum.valueOf(value.toUpperCase());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, FuncaoEnum value) {
		return value == null ? "" : value.name();
	}

}
