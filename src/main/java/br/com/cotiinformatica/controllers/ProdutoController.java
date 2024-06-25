package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

	@PostMapping()
	public ProdutoResponseDto post(@RequestBody ProdutoRequestDto request) throws Exception {

		Produto produto = modelMapper.map(request, Produto.class);
		produto.setCategoria(new Categoria());
		produto.getCategoria().setId(request.getCategoriaId());

		ProdutoRepository produtoRepository = new ProdutoRepository();
		produtoRepository.create(produto);

		Produto produtoCadastrado = produtoRepository.findById(produto.getId());

		return modelMapper.map(produtoCadastrado, ProdutoResponseDto.class);
	}

	@PutMapping("{id}")
	public ProdutoResponseDto put(@PathVariable Integer id, @RequestBody ProdutoRequestDto request) throws Exception {

		Produto produto = modelMapper.map(request, Produto.class);
		produto.setId(id);

		produto.setCategoria(new Categoria());
		produto.getCategoria().setId(request.getCategoriaId());

		ProdutoRepository produtoRepository = new ProdutoRepository();
		produtoRepository.update(produto);

		Produto produtoAtualizado = produtoRepository.findById(produto.getId());

		return modelMapper.map(produtoAtualizado, ProdutoResponseDto.class);
	}

	@DeleteMapping("{id}")
	public ProdutoResponseDto delete(@PathVariable Integer id) throws Exception {

		ProdutoRepository produtoRepository = new ProdutoRepository();
		Produto produto = produtoRepository.findById(id);

		produtoRepository.delete(id);

		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@GetMapping("{id}")
	public ProdutoResponseDto getById(@PathVariable Integer id) throws Exception {

		ProdutoRepository produtoRepository = new ProdutoRepository();
		Produto produto = produtoRepository.findById(id);
		
		return modelMapper.map(produto, ProdutoResponseDto.class);
	}

	@GetMapping
	public List<ProdutoResponseDto> getAll() throws Exception {

		ProdutoRepository produtoRepository = new ProdutoRepository();
		List<Produto> produtos = produtoRepository.findAll();

		List<ProdutoResponseDto> response = produtos.stream()
				.map(produto -> modelMapper.map(produto, ProdutoResponseDto.class)).collect(Collectors.toList());

		return response;
	}
}
