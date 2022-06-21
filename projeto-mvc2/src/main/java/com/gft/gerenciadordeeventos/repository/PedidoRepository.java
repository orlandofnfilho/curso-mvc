package com.gft.gerenciadordeeventos.repository;

import com.gft.gerenciadordeeventos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select * from pedido where usuario_id = :id", nativeQuery = true)
    List<Pedido> findAllPedidoByUser(@Param("id") Long id);
}
