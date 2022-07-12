package br.com.acelera.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.acelera.biblioteca.entities.AutorEntity;
import br.com.acelera.biblioteca.exceptions.NotFoundBusinessException;
import br.com.acelera.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public AutorEntity cria(AutorEntity autorEntity) {
		return autorRepository.save(autorEntity);
	}
	
	public AutorEntity atualiza(AutorEntity autorEntity) {
		return autorRepository.save(autorEntity);
	}
	
	public AutorEntity buscaPeloId(Long id) {
		Optional<AutorEntity> encontrou = autorRepository.findById(id);
		if(encontrou.isPresent()) {			
			return encontrou.get();
		}else {
			throw new NotFoundBusinessException("Autor " + id + " não foi encontrado!");
		}
	}
	
	public Page<AutorEntity> listaTodos(Pageable paginacao){
		Page<AutorEntity> encontrou = autorRepository.findAll(paginacao);
		return encontrou;
	}
}
