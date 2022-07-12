package br.com.acelera.biblioteca.converts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.acelera.biblioteca.configs.dto.inputs.LivroInput;
import br.com.acelera.biblioteca.configs.dto.outputs.LivroOutput;
import br.com.acelera.biblioteca.configs.dto.outputs.LivroSemAutorOutput;
import br.com.acelera.biblioteca.entities.LivroEntity;

@Component
public class LivroConvert {

	@Autowired
	private ModelMapper modelMapper;
	
	public LivroEntity inputToNewEntity(@Valid LivroInput livroInput) {
		return modelMapper.map(livroInput, LivroEntity.class);
	}
	
	public LivroOutput entityToOutput(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroOutput.class);
	}
	
	public void copyInputToEntity(LivroEntity livroLocalizado, @Valid LivroInput livroInput) {
		modelMapper.map(livroInput, livroLocalizado);
	}
	
	public LivroSemAutorOutput entityToSemAutorOutput(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroSemAutorOutput.class);
	}
	
	public Page<LivroOutput> listPageEntityToListPageOutput(Page<LivroEntity> livroLocalizados){
		return livroLocalizados.map(this::entityToOutput);
	}
	
	public Page<LivroSemAutorOutput> listPageEntityToListPageOutputCopy(Page<LivroEntity> livros){
		return livros.map(this::entityToSemAutorOutput);
	}
}
