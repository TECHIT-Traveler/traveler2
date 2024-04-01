package com.ll.traveler.domain.place.place.repository;

import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.entity.Ulsan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByIdLessThan(long id);
}
