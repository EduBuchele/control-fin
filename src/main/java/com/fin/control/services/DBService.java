package com.fin.control.services;

import com.fin.control.entities.PessoaEntity;
import com.fin.control.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public void instantiateTestDatabase() {

        PessoaEntity p1 = new PessoaEntity("Aline Silva","000.111.000-88", "aline@test.com");
        PessoaEntity p2 = new PessoaEntity("Joao Silva","000.222.000-88", "joao@test.com");

        pessoaRepository.saveAll(List.of(p1, p2));
    }
}
