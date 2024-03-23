package com.ll.traveler.domain.place.place.repository;

import com.ll.traveler.domain.place.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
