package com.fin.control.controllers;

import com.fin.control.dto.PessoaDTO;
import com.fin.control.entities.PessoaEntity;
import com.fin.control.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PessoaEntity> find(@PathVariable Integer id) {
        PessoaEntity obj = pessoaService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaEntity> list = pessoaService.findAll();
        List<PessoaDTO> listDTO = list.stream().map(o -> new PessoaDTO(o)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PessoaDTO objDTO) {
        PessoaEntity obj = pessoaService.fromDTO(objDTO);
        obj = pessoaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO objDTO, @PathVariable Integer id) {
        PessoaEntity obj = pessoaService.fromDTO(objDTO);
        obj.setId(id);
        pessoaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
