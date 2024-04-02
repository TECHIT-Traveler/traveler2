package com.ll.traveler.domain.place.place.ulsan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UlsanApiRepository extends JpaRepository<Ulsan, Long> {
    List<Ulsan> findAllByIdLessThan(long id);

}
