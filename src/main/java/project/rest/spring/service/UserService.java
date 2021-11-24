package project.rest.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import project.rest.spring.repository.UserRepository;
import project.rest.spring.repository.banco.CpfRepository;
import project.rest.spring.repository.banco.NomeRepository;

public class RequestProcess {

    @Autowired
    private final UserRepository UserRepository;
    @Autowired
    private final NomeRepository nomeRepository;
    @Autowired
    private final CpfRepository CpfRepository;


    public RequestProcess(project.rest.spring.repository.UserRepository userRepository, NomeRepository nomeRepository, CpfRepository cpfRepository) {
        this.UserRepository = userRepository;
        this.nomeRepository = nomeRepository;
        this.CpfRepository = cpfRepository;

    }
}
