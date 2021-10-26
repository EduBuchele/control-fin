package com.fin.control.services;

import com.fin.control.dto.PessoaDTO;
import com.fin.control.entities.PessoaEntity;
import com.fin.control.repositories.PessoaRepository;
import com.fin.control.services.exceptions.DataIntegrityException;
import com.fin.control.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaEntity find(Integer id) {
        Optional<PessoaEntity> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado Id: " + id + ", Tipo: " + PessoaEntity.class.getName()));
    }

    public List<PessoaEntity> findAll() {
        return pessoaRepository.findAll();
    }

    public void delete(Integer id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir pessoa.");
        }
    }

    public PessoaEntity update(PessoaEntity obj) {
        PessoaEntity newObjt = find(obj.getId());
        updateData(newObjt, obj);
        return pessoaRepository.save(obj);
    }

    private void updateData(PessoaEntity newObjt, PessoaEntity obj) {
        newObjt.setNome(obj.getNome());
        newObjt.setEmail(obj.getEmail());
    }

    @Transactional
    public PessoaEntity insert(PessoaEntity obj) {
        obj.setId(null);
        return pessoaRepository.save(obj);
    }

    public PessoaEntity fromDTO(PessoaDTO objDTO) {
        return new PessoaEntity(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getEmail());
    }

}
