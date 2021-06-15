package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.Lines;

@Repository
public interface LinesRepository extends JpaRepository<Lines, Long> {
}
