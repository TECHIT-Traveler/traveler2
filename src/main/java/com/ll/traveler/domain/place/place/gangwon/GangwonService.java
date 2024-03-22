package com.ll.traveler.domain.place.place.gangwon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor // 필드 주입을 위한 생성자 생성
@Transactional(readOnly = true) // 읽기전용
public class GangwonService {
    private final GangwonRepository gangwonRepository;

    public List<Gangwon> getAllGangwonData() {
        return gangwonRepository.findAll();
    }
}
