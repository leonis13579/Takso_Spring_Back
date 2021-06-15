package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.Drivers;

@Repository
public interface DriversRepository extends JpaRepository<Drivers, Long> {
}
