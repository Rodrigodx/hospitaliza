package com.rodrigo.hospitaliza.enums;

public enum EspecialidadeEnum {

	CARDIOLOGISTA ("CARDIOLOGISTA"), ORTOPEDISTA ("ORTOPEDISTA"), FONODIOLOGO ("FONODIOLOGO"), PEDIATRA ("PEDIATRA");

	public final String label;

	EspecialidadeEnum (String label) {
		this.label = label;
	}

}
