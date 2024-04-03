package com.ll.traveler.domain.place.placeLike.repository;

import com.ll.traveler.domain.place.placeLike.entity.PlaceLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceLikeRepository extends JpaRepository<PlaceLike, Long> {
    List<PlaceLike> findPlaceLikesByMemberId(long id);
}
