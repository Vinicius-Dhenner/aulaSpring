package br.com.anpede.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.anpede.dto.AssociadoDTO;
import br.com.anpede.services.AssociadoService;

@RestController
@RequestMapping(value = "/associados")
public class AssociadoResource {
	
	@Autowired
	private AssociadoService service;
	
	@GetMapping
	public ResponseEntity<List<AssociadoDTO>> findAll() {
		List<AssociadoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping	public AssociadoDTO insert(AssociadoDTO dto) {
		Associado entity = new Associado();
		entity.setNome(dto.getNome());
		entity.setCPF(dto.getCPF());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setEndereco(dto.getEndereco());
		
		entity = repository.save(entity);
		
		return new AssociadoDTO(entity);
	}
	@RequestMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){	
		AssociadoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<AssociadoDTO> insert(@RequestBody AssociadoDTO dto){	
		service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).bo0dy(null);
		
	}
	
	@PutMapping(value = "/{id})
	public ResponseEntity<AssociadoDTO> update(@Pathvariable Long id, @RequestBody AssociadoDTO dto){	
		service.updtae(dto);
		return ResponseEntity.created(uri).body(dto);
		
	}
}
