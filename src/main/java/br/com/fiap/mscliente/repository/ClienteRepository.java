package br.com.fiap.mscliente.repository;

import br.com.fiap.mscliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Long> {

}
