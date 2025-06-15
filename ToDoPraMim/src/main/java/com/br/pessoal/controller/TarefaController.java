package com.br.pessoal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ctarefa/")
@CrossOrigin(origins="*")
@Validated
public class TarefaController {

	@Autowired
	private TarefaRepository tRep;
	
	//GET - http://localhost:8080/ctarefa/tarefas
	@GetMapping("/tarefas")
	public List<Tarefa> listar() {
		
		return this.tRep.findAll(Sort.by(Sort.Direction.ASC, "id"));
		
	}
	
	//GET - http://localhost:8080/ctarefa/tarefas/{id}
	@GetMapping("/tarefas/{id}")
	public ResponseEntity<Tarefa> consultar(@PathVariable Long id) {
		
		Tarefa tarefa = tRep.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id)); 
		
						return ResponseEntity.ok(tarefa);
	}
	
	//POST - http://localhost:8080/ctarefa/tarefas
	@PostMapping("/tarefas")
	public ResponseEntity<Map<String, Object>> inserir(@Valid @RequestBody Tarefa tnova) {
		
		if (tRep.existsByNome(tnova.getNome())) {
			throw new IllegalArgumentException("Já existe tarefa com esse nome");
		}
		tRep.save(tnova); 
		
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("success", true);
		resposta.put("message", "Tarefa criada com sucesso");
		resposta.put("data", tnova);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(resposta);
	}
	
	//PUT - http://localhost:8080/ctarefa/tarefas/{id}
	@PutMapping("/tarefas/{id}")
	public ResponseEntity<Map<String, Object>> alterar(@PathVariable Long id, @RequestBody Tarefa tarefa){
		
		Tarefa tarefaConsulta = tRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
		
		tarefaConsulta.setNome(tarefa.getNome());
		tarefaConsulta.setDescricao(tarefa.getDescricao());
		
		tRep.save(tarefaConsulta); 
		
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("sucess", true);
		resposta.put("message", "Tarefa atualizada com sucesso");
		resposta.put("data", tarefaConsulta);
		
		return ResponseEntity.ok(resposta);
	}
	
	//DELETE - http://localhost:8080/ctarefa/tarefas/{id}
	@DeleteMapping("/tarefas/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id){
		
		Tarefa tarefa = tRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
		
		tRep.delete(tarefa); 
		
		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Tarefa excluída", Boolean.TRUE);
		
		return ResponseEntity.ok(resposta);
	}
}
