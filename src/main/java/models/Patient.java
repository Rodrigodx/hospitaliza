package models;

import java.time.LocalDate;

import enums.GenderEnum;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nome não pode ser nulo ou vazio.")
	private String name;
	
	@NotBlank
	private String cpf;
	
	@Past
	@NotNull
	@JsonbDateFormat(value = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotNull
	@Min(value = 11)
	@Max (value = 20)
	private String phone;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private GenderEnum gender;
}
