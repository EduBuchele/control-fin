package com.fin.control.repositories;

import com.fin.control.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer>{

	@Transactional(readOnly = true)
	PessoaEntity findByCpf(String cpf);
	
}
