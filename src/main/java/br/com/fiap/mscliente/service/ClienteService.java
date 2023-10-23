package br.com.fiap.mscliente.service;

import br.com.fiap.mscliente.dto.ClienteDTO;
import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> getAll(){
        List<Cliente> list = repository.findAll();
        return list.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO getById(Long id){
        Cliente cliente = repository.findById(id).orElseThrow();
        return new ClienteDTO(cliente);
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity){
      entity.setNome(dto.getNome());
      entity.setEmail(dto.getEmail());
      entity.setEndereco(dto.getEndereco());
      entity.setTelefone(dto.getTelefone());
      entity.setSenha(dto.getSenha());
    }

    @Transactional(readOnly = true)
    public ClienteDTO insert(ClienteDTO dto){
        Cliente cliente = new Cliente();
        copyDtoToEntity(dto, cliente);
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO update(ClienteDTO dto, Long id){
        Cliente cliente = repository.getReferenceById(id);
        copyDtoToEntity(dto, cliente);
        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
