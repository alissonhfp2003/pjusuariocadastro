package com.cadastro.pjusuariocadastro.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cadastro.pjusuariocadastro.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cadastro.pjusuariocadastro.Model.Usuario;
import com.cadastro.pjusuariocadastro.Repository.UsuarioCadasatroRepository;


@RestController
@RequestMapping("/usuario") 
public class UsuarioCadastroController {

    @Autowired
    private UsuarioCadasatroRepository repository;

    //Metodo para inserir um usuario
    @PutMapping("/usuario")
    public Usuario inserir(@PathVariable Usuario usuario) {
        return this.repository.save(usuario);
    }

    //Metodo para alterar os dados do usuario utilizando o ID
    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable Long id, @RequestBody Usuario usuario) {

        Usuario usuarioRecuperado = this.repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Usuario não encontrado: " + id));

        usuarioRecuperado.setCodigo(id);
        usuarioRecuperado.setLogin(usuario.getLogin());
        usuarioRecuperado.setPassword(usuario.getPassword());

        Usuario atualizado = this.repository.save(usuarioRecuperado);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/aluno{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id){

        Usuario usuarioRecuperado = this.repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Usuario não encontrado: " + id));

        this.repository.delete(usuarioRecuperado);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Usuario excluido: ", Boolean.TRUE);

        return  ResponseEntity.ok(resposta);
    }
}
