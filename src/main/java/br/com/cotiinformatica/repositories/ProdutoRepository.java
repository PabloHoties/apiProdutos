package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ProdutoRepository {

	public void create(Produto produto) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO produto (nome, preco, quantidade, categoria_id) VALUES (?,?,?,?)");
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getCategoria().getId());
		statement.execute();

		connection.close();
	}

	public void update(Produto produto) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("UPDATE produto SET nome=?, preco=?, quantidade=?, categoria_id=? WHERE id=?");
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getCategoria().getId());
		statement.setInt(5, produto.getId());
		statement.execute();

		connection.close();
	}

	public void delete(Integer id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("DELETE from produto WHERE id=?");
		statement.setInt(1, id);
		statement.execute();

		connection.close();
	}

	public Produto findById(Integer id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("SELECT p.id AS idproduto, p.nome AS nomeproduto, p.preco, p.quantidade, c.id AS idcategoria, c.nome AS nomecategoria from produto p "
						+ "INNER JOIN categoria c ON c.id = p.categoria_id WHERE id=?");
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		Produto produto = null;

		if (resultSet.next()) {

			produto = new Produto();
			produto.setCategoria(new Categoria());
			
			produto.setId(resultSet.getInt("idproduto"));
			produto.setNome(resultSet.getString("nomeproduto"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));

			produto.getCategoria().setId(resultSet.getInt("idcategoria"));
			produto.getCategoria().setNome(resultSet.getString("nomecategoria"));
		}
		connection.close();
		return produto;
	}

	public List<Produto> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT p.id AS idproduto, p.nome AS nomeproduto, p.preco, p.quantidade, c.id AS idcategoria, c.nome AS nomecategoria from produto p "
				+ "INNER JOIN categoria c ON c.id = p.categoria_id ORDER BY nome ASC");

		ResultSet resultSet = statement.executeQuery();

		List<Produto> produtos = new ArrayList<Produto>();

		while (resultSet.next()) {

			Produto produto = new Produto();
			produto.setCategoria(new Categoria());

			produto.setId(resultSet.getInt("idproduto"));
			produto.setNome(resultSet.getString("nomeproduto"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setQuantidade(resultSet.getInt("quantidade"));

			produto.getCategoria().setId(resultSet.getInt("idcategoria"));
			produto.getCategoria().setNome(resultSet.getString("nomecategoria"));

			produtos.add(produto);
		}
		connection.close();
		return produtos;
	}
}
