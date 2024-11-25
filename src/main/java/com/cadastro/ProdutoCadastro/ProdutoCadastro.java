package com.cadastro.ProdutoCadastro;
import java.util.ArrayList;
import java.util.List;

public class ProdutoCadastro {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        validarProduto(produto);
        for (Produto p : produtos) {
            if (p.getCodigo().equals(produto.getCodigo())) {
                throw new IllegalArgumentException("Produto com este código já existe");
            }
        }
        produtos.add(produto);
    }

    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    public void atualizar(String codigo, String novoNome, String novaDescricao, double novoPreco) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) {
                if (novoNome == null || novoNome.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome do produto é obrigatório");
                }
                if (novaDescricao.length() > 200) {
                    throw new IllegalArgumentException("Descrição não pode ultrapassar 200 caracteres");
                }
                if (novoPreco <= 0) {
                    throw new IllegalArgumentException("Preço deve ser maior que zero");
                }
                // Atualiza os dados do produto
                p.setNome(novoNome);
                p.setDescricao(novaDescricao);
                p.setPreco(novoPreco);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado");
    }

    public List<Produto> getAll() {
        return produtos;
    }

    private void validarProduto(Produto produto) {
        if (produto.getCodigo() == null || produto.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do produto é obrigatório");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (produto.getDescricao().length() > 200) {
            throw new IllegalArgumentException("Descrição não pode ultrapassar 200 caracteres");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
    }
}