package com.exemplodto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplodto.dto.UsuarioDTO;
import com.exemplodto.entities.Usuario;
import com.exemplodto.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid UsuarioDTO usuarioDTO){
		UsuarioDTO salvarUsuario = usuarioService.salvar(usuarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO){
		UsuarioDTO alteraUsuarioDTO = usuarioService.atualizar(id, usuarioDTO);
		if(alteraUsuarioDTO != null) {
			return ResponseEntity.ok(alteraUsuarioDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> apagar(@PathVariable Long id){
		boolean apagar = usuarioService.deletar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscarPorId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping 
	public ResponseEntity<List<Usuario>> buscaTodos(){
		List<Usuario> usuario = usuarioService.buscarTodos();
		return ResponseEntity.ok(usuario);
	}

}
