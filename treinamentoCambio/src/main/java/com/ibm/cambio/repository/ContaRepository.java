package com.ibm.cambio.repository;
import com.ibm.cambio.model.Conta;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.*;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}