package br.com.acelera.biblioteca.configs.dto.inputs;

import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroInput {

	@Length(max = 200,message = "O titulo deve ter no máximo 200 caracteres")
	@NotBlank(message = "Titulo é obrigatório!")
	private String titulo;
	
	@DecimalMin("1000")
	@DecimalMax("9999")
	@NotNull(message = "Ano lançamento é obrigatório!")
	private Integer anoLancamento;
	
	@NotEmpty(message = "Deve ter ao menos 1 autor!")
	private List<Long> idsAutores;
}
