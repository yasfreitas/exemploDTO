package com.exemplodto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplodto.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
