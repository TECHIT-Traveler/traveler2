package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ll.traveler.domain.place.place.entity.Place;
import com.ll.traveler.domain.place.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ll.traveler.domain.place.place.entity.Ulsan;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UlsanApiService {
    private final PlaceRepository placeRepository;
//    private final UlsanApiRepository ulsanApiRepository;

    @Transactional
    public List<Place> mapXmlToUlsanList(String xmlData) throws IOException {
        List<Place> ulsanList = new ArrayList<>();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode rootNode = xmlMapper.readTree(xmlData);

        JsonNode bodyNode = rootNode.get("body");
        JsonNode dataListNode = bodyNode.get("data");
        JsonNode listNode = dataListNode.get("list");

        for (JsonNode node : listNode) {
            Place place = Ulsan.builder()
                    .unqId(getStringValue(node, "unqId"))
                    .city(getStringValue(node, "city"))
                    .name(getStringValue(node, "facility"))
//                    .bsnsStts(getStringValue(node, "bsnsStts"))
                    .contact(getStringValue(node, "tel"))
                    .cls(getStringValue(node, "cls"))
                    .remarks(getStringValue(node, "remarks"))
                    .zipCode(getStringValue(node, "zipCode"))
                    .address(getStringValue(node, "streetNameAddress"))
                    .address(getStringValue(node, "address"))
                    .latitude(getStringValue(node, "lat"))
                    .longitude(getStringValue(node, "lng"))
                    .rgstDate(getStringValue(node, "rgstDate"))
                    .build();

            ulsanList.add(placeRepository.save(place));
        }

        return ulsanList;
    }
    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        return fieldValue != null && !fieldValue.isNull() ? fieldValue.asText() : "-";
    }
    public List<Place> getAllUlsanData() {
        return placeRepository.findAllByIdLessThan(30);
    }


    public Place getUlsanDataById(Long id) {
        Optional<Place> ulsanOptional = placeRepository.findById(id);
        return ulsanOptional.orElse(null);
    }

}
