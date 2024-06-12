package com.vinastore.repositories;

import com.vinastore.entities.Galleries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepositories extends JpaRepository<Galleries, Integer> {
}
