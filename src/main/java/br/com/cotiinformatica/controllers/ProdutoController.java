package br.com.cotiinformatica.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.dtos.ProdutoResponseDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("criar")
	public ProdutoResponseDto post(@RequestBody ProdutoRequestDto request) throws Exception {

		Produto produto = modelMapper.map(request, Produto.class);
		produto.setCategoria(new Categoria());
		produto.getCategoria().setId(request.getCategoriaId());

		ProdutoRepository produtoRepository = new ProdutoRepository();
		produtoRepository.create(produto);

		Produto produtoCadastrado = produtoRepository.findById(produto.getId());
		
		return modelMapper.map(produtoCadastrado, ProdutoResponseDto.class);
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
	 * @GetMapping() public void getById(@PathVariable("id") Integer id) throws
	 * Exception {
	 * 
	 * // TODO }
	 */

	@GetMapping()
	public void getAll() {

		// TODO
	}
}
