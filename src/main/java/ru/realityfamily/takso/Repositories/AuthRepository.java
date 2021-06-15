package ru.realityfamily.takso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.realityfamily.takso.Models.AuthData;

@Repository
public interface AuthRepository extends JpaRepository<AuthData, Long> {
}
