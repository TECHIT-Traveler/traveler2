package com.ll.traveler.domain.place.place.gangwon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Gangwon2ApiRepository extends JpaRepository<Gangwon2, Long> {
    List<Gangwon2> findAllByNameContaining(String name);

    List<Gangwon2> findAllByStreetAddressContaining(String address);
    List<Gangwon2> findAllByIdLessThan(long id);
}



