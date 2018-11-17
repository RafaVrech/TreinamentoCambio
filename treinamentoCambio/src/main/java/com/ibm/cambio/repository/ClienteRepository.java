package com.ibm.cambio.repository;
import com.ibm.cambio.model.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.*;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
