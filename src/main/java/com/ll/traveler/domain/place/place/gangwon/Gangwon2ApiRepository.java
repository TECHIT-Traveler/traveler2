package com.ll.traveler.domain.place.place.gangwon;

import com.ll.traveler.domain.place.place.ulsan.Ulsan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Gangwon2ApiRepository extends JpaRepository<Gangwon2, Long> {
    List<Gangwon2> findAllByIdLessThan(long id);
}
