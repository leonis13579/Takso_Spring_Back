package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
