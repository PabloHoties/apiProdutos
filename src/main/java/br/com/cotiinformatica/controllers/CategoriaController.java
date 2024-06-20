package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@GetMapping
	public List<CategoriaResponseDto> getAll() throws Exception {

		CategoriaRepository categoriaRepository = new CategoriaRepository();

		List<Categoria> categorias = categoriaRepository.findAll();

		List<CategoriaResponseDto> response = new ArrayList<>();

		for (Categoria categoria : categorias) {

			CategoriaResponseDto dto = new CategoriaResponseDto();
			dto.setId(categoria.getId());
			dto.setNome(categoria.getNome());

			response.add(dto);
		}
		return response;
	}
}
