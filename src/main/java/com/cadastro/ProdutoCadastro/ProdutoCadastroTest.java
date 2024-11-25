package com.cadastro.ProdutoCadastro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoCadastroTest {
    private ProdutoCadastro produtoCadastro;

    @BeforeEach
    public void setUp() {
        produtoCadastro = new ProdutoCadastro();
    }

    @Test
    public void testAdicionarProdutoComCodigoExistente() {
        Produto produto1 = new Produto("001", "Produto 1", "Descrição 1", 10.0);
        produtoCadastro.adicionar(produto1);
        Produto produto2 = new Produto("001", "Produto 2", "Descrição 2", 20.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoCadastro.adicionar(produto2);
        });

        assertEquals("Produto com este código já existe", exception.getMessage());
    }

    @Test
    public void testAdicionarProdutoComNomeVazio() {
        Produto produto = new Produto("002", "", "Descrição 1", 10.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoCadastro.adicionar(produto);
        });

        assertEquals("Nome do produto é obrigatório", exception.getMessage());
    }

    @Test
    public void testAdicionarProdutoComDescricaoExcedendoLimite() {
        Produto produto = new Produto("003", "Produto 3","A".repeat(201), 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoCadastro.adicionar(produto);
        });
        assertEquals("Descrição não pode ultrapassar 200 caracteres", exception.getMessage());
    }

    @Test
    public void testAdicionarProdutoComPrecoNegativo(){
        Produto produto = new Produto("004","Produto 04", "Teste de valor negativo", -100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produtoCadastro.adicionar(produto);
        });
    }
}
