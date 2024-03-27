package com.ll.traveler.domain.place.place.gangwon;

import groovyjarjarantlr4.v4.gui.PostScriptDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Gangwon2ApiRepository extends JpaRepository<Gangwon2, Long> {
    List<Gangwon2> findAllByNameContaining(String name);

    List<Gangwon2> findAllByStreetAddressContaining(String address);
}
