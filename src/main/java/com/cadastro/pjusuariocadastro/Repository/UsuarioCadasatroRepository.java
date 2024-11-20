package com.cadastro.pjusuariocadastro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadastro.pjusuariocadastro.Model.Usuario;

@Repository
public interface UsuarioCadasatroRepository extends JpaRepository<Usuario, Long>{

}
