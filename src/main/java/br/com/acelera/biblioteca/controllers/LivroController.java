package br.com.acelera.biblioteca.controllers;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acelera.biblioteca.configs.ControllerConfig;
import br.com.acelera.biblioteca.configs.dto.inputs.LivroInput;
import br.com.acelera.biblioteca.configs.dto.outputs.LivroOutput;
import br.com.acelera.biblioteca.converts.LivroConvert;
import br.com.acelera.biblioteca.entities.LivroEntity;
import br.com.acelera.biblioteca.services.LivroService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/livros")
public class LivroController {

	@Autowired
	private LivroConvert livroConvert;
	
	@Autowired
	private LivroService livroService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public LivroOutput cria(@RequestBody @Valid LivroInput livroInput) {
		LivroEntity livroEntity = livroConvert.inputToNewEntity(livroInput);
		LivroEntity livroCriado = livroService.cria(livroEntity, livroInput);
		return livroConvert.entityToOutput(livroCriado);
	}
	
	@PutMapping("/{id}")
	public LivroOutput alterar(@PathVariable Long id, @RequestBody @Valid LivroInput livroInput) {
		LivroEntity livroLocalizado = livroService.buscaLivroPeloId(id);
		livroConvert.copyInputToEntity(livroLocalizado, livroInput);
		LivroEntity livroAlterado = livroService.altera(livroLocalizado, livroInput);
		return livroConvert.entityToOutput(livroAlterado);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		LivroEntity livroLocalizado = livroService.buscaLivroPeloId(id);
		livroService.deleta(livroLocalizado);
	}
	
	@GetMapping
	public Page<LivroOutput> listaTodos(
			@ParameterObject @PageableDefault(page = 0,size = 5,sort = "titulo",direction = Direction.ASC) Pageable paginacao){
		Page<LivroEntity> livroLocalizados = livroService.listaTodos(paginacao);
		return livroConvert.listPageEntityToListPageOutput(livroLocalizados);
	}
	
}















