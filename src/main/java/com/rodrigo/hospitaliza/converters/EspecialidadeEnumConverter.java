package com.rodrigo.hospitaliza.converters;

import com.rodrigo.hospitaliza.enums.EspecialidadeEnum;
import com.rodrigo.hospitaliza.enums.FuncaoEnum;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@FacesConverter(forClass = EspecialidadeEnum.class)
@Named
public class EspecialidadeEnumConverter implements Converter<EspecialidadeEnum> {

	@Override
	public EspecialidadeEnum getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) return null;
		return EspecialidadeEnum.valueOf(value.toUpperCase());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, EspecialidadeEnum value) {
		return value == null ? "" : value.name();
	}

}
