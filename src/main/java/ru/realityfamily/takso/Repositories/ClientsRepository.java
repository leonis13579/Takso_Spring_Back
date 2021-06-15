package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
