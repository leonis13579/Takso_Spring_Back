package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.Cars;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
}
