package com.rodrigo.hospitaliza.converters;

import com.rodrigo.hospitaliza.enums.FuncaoEnum;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

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
