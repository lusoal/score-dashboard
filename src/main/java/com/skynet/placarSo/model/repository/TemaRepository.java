package com.skynet.placarSo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skynet.placarSo.model.bean.Temas;

@Repository
public interface TemaRepository extends JpaRepository<Temas, Long> {

}
