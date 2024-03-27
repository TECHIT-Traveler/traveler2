package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UlsanApiService {
    private final UlsanApiRepository ulsanApiRepository;

    @Transactional
    public List<Ulsan> mapXmlToUlsanList(String xmlData) throws IOException {
        List<Ulsan> ulsanList = new ArrayList<>();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode rootNode = xmlMapper.readTree(xmlData);

        JsonNode bodyNode = rootNode.get("body");
        JsonNode dataListNode = bodyNode.get("data");
        JsonNode listNode = dataListNode.get("list");

        for (JsonNode node : listNode) {
            Ulsan ulsan = Ulsan.builder()
                    .unqId(node.get("unqId").asText())
                    .city(node.get("city").asText())
                    .facility(node.get("facility").asText())
                    //.lcnsInfo(node.get("lcnsInfo").asText())
                    //.lcnsAprDate(node.get("lcnsAprDate").asText())
                    //.lcnsCncDate(node.get("lcnsCncDate").asText())
                    .bsnsStts(node.get("bsnsStts").asText())
                    //.failDate(node.get("failDate").asText())
                    .tel(node.get("tel").asText())
                    //.wkdBsn(node.get("wkdBsn").asText())
                    //.wknBsn(node.get("wknBsn").asText())
                    .cls(node.get("cls").asText())
                    .remarks(node.get("remarks").asText())
                    .zipCode(node.get("zipCode").asText())
                    .streetNameAddress(node.get("streetNameAddress").asText())
                    .address(node.get("address").asText())
                    .lat(node.get("lat").asText())
                    .lng(node.get("lng").asText())
                    .rgstDate(node.get("rgstDate").asText())
                    .build();

            ulsanList.add(ulsanApiRepository.save(ulsan));
        }

        return ulsanList;
    }

    public List<Ulsan> getAllUlsanData() {
        return ulsanApiRepository.findAllByIdLessThan(30);
    }
    public Ulsan getUlsanDataById(Long id) {
        Optional<Ulsan> ulsanOptional = ulsanApiRepository.findById(id);
        return ulsanOptional.orElse(null);
    }

    public List<Ulsan> searchFacility(String facility) {
        return ulsanApiRepository.findAllByFacilityContaining(facility);
    }

    public List<Ulsan> searchCity(String city) {
        return ulsanApiRepository.findAllByCityContaining(city);
    }

    @Transactional
    public void like(Ulsan ulsan, Member member) {
        ulsan.like(member);
    }

    @Transactional
    public void cancelLike(Ulsan ulsan, Member member) {
        ulsan.cancelLike(member);
    }
}
