package br.com.acelera.biblioteca.configs.dto.inputs;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorInput {

	@Length(max = 100,message = "O nome deve ter no máximo 100 caracteres")
	@NotBlank(message = "Nome é obrigatório!")
	private String nome;
	
	@Length(max = 1000,message = "A biografia deve ter no máximo 1000 caracteres")
	@NotBlank(message = "Biografia é obrigatória!")
	private String biografia;
}
