package com.rodrigo.hospitaliza.enums;

public enum FuncaoEnum {
	
	ATENDENTE("ATENDENTE"),
	ENFERMEIRO("ENFERMEIRO"),
	MEDICO_CARDIOLOGISTA("MEDICO_CARDIOLOGISTA"),
	MEDICO_ORTOPEDISTA("MEDICO_ORTOPEDISTA"),
	CLINICO_GERAL("CLINICO_GERAL"),
	ADMIN("ADMIN");
	
	public final String label;
	
	FuncaoEnum (String label) {
		this.label = label;
	}
}
