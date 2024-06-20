package br.com.cotiinformatica.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@PostMapping("criar")
	public ProdutoResponseDto post(@RequestBody ProdutoRequestDto dto) throws Exception {

		// TODO

		Produto produto = new Produto();
		produto.setCategoria(new Categoria());

		produto.setNome(dto.getNome());
		produto.setPreco(dto.getPreco());
		produto.setQuantidade(dto.getQuantidade());
		produto.getCategoria().setId(dto.getCategoriaId());

		ProdutoRepository produtoRepository = new ProdutoRepository();
		produtoRepository.create(produto);

		ProdutoResponseDto response = new ProdutoResponseDto();
		response.setCategoria(new CategoriaResponseDto());

		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setPreco(produto.getPreco());
		response.setQuantidade(produto.getQuantidade());
		response.getCategoria().setId(produto.getCategoria().getId());
		response.getCategoria().setNome(produto.getCategoria().getNome());

		return response;
	}

	@PutMapping()
	public void put() throws Exception {

		// TODO
	}

	@DeleteMapping()
	public void delete(@PathVariable("id") Integer id) throws Exception {

		// TODO
	}
	
	/*
	@GetMapping()
	public void getById(@PathVariable("id") Integer id) throws Exception {

		// TODO
	}
	*/
	
	@GetMapping()
	public void getAll() {

		// TODO
	}
}
