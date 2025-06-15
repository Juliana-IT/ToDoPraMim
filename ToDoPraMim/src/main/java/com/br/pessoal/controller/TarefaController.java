package com.br.pessoal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pessoal.exception.ResourceNotFoundException;
import com.br.pessoal.model.Tarefa;
import com.br.pessoal.repository.TarefaRepository;

@RestController
@RequestMapping("/ctarefa/")
@CrossOrigin(origins="*")
public class TarefaController {

	@Autowired
	private TarefaRepository tRep;
	
	//GET - http://localhost:8080/ctarefa/tarefa
	@GetMapping("/tarefa")
	public List<Tarefa> listar() {
		
		return this.tRep.findAll(Sort.by(Sort.Direction.ASC, "id"));
		
	}
	
	//GET - http://localhost:8080/ctarefa/tarefa/{id}
	@GetMapping("/tarefa/{id}")
	public ResponseEntity<Tarefa> consultar(@PathVariable Long id) {
		
		Tarefa tarefa = tRep.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id)); 
		
						return ResponseEntity.ok(tarefa);
	}
	
	//POST - http://localhost:8080/ctarefa/tarefa
	@PostMapping("/tarefa")
	public Tarefa inserir(@RequestBody Tarefa tarefa) {
		
		return tRep.save(tarefa); 
	}
	
	//PUT - http://localhost:8080/ctarefa/tarefa/{id}
	@PutMapping("/tarefa/{id}")
	public ResponseEntity<Tarefa> alterar(@PathVariable Long id, @RequestBody Tarefa tarefa){
		
		Tarefa tarefaConsulta = tRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
		
		tarefaConsulta.setId(id);
		tarefaConsulta.setNome(tarefa.getNome());
		tarefaConsulta.setDescricao(tarefa.getDescricao());
		
		tRep.save(tarefaConsulta); 
		
		return ResponseEntity.ok(tarefaConsulta);
	}
	
	//DELETE - http://localhost:8080/ctarefa/tarefa/{id}
	@DeleteMapping("/tarefa/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id){
		
		Tarefa tarefa = tRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
		
		tRep.delete(tarefa); 
		
		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Tarefa excluída", Boolean.TRUE);
		
		return ResponseEntity.ok(resposta);
	}
}
