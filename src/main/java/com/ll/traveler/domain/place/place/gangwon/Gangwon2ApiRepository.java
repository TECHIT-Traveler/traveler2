package com.ll.traveler.domain.place.place.gangwon;

import groovyjarjarantlr4.v4.gui.PostScriptDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Gangwon2ApiRepository extends JpaRepository<Gangwon2, Long> {
    List<Gangwon2> findAllBy업체명Containing(String location);

//    List<Gangwon2> findAllBy도로명_주소Containing(String address);
}
