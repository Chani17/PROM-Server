package inu.thebite.toryaba.repository;


import inu.thebite.toryaba.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, String> {

}
