package org.casadocodigo.spring_kafka.repository;
import org.casadocodigo.spring_kafka.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository
        extends JpaRepository<Shop, Long> {
}

