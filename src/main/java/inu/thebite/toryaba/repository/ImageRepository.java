package inu.thebite.toryaba.repository;

import inu.thebite.toryaba.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByCategoryId(Long categoryId);

    Optional<Image> findByName(String imageName);

    void deleteByName(String imageName);
}
