package com.exemplodto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplodto.dto.UsuarioDTO;
import com.exemplodto.entities.Usuario;
import com.exemplodto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.nome(),usuarioDTO.senha());
		Usuario salvarUsuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(salvarUsuario.getId(), salvarUsuario.getNome(), salvarUsuario.getSenha());
	}
	
	public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
		Usuario existeUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		
		existeUsuario.setNome(usuarioDTO.nome());
		existeUsuario.setSenha(usuarioDTO.senha());
		
		Usuario updateUsuario = usuarioRepository.save(existeUsuario);
		return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha());
	}
	
	public boolean deletar(long id) {
		Optional <Usuario> existeUsuario = usuarioRepository.findById(id);
		if (existeUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Usuario> buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		Optional <Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElse(null);
	}

}
