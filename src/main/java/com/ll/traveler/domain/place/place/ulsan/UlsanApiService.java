package com.ll.traveler.domain.place.place.ulsan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                    .unqId(getStringValue(node, "unqId"))
                    .city(getStringValue(node, "city"))
                    .facility(getStringValue(node, "facility"))
                    .bsnsStts(getStringValue(node, "bsnsStts"))
                    .tel(getStringValue(node, "tel"))
                    .cls(getStringValue(node, "cls"))
                    .remarks(getStringValue(node, "remarks"))
                    .zipCode(getStringValue(node, "zipCode"))
                    .streetNameAddress(getStringValue(node, "streetNameAddress"))
                    .address(getStringValue(node, "address"))
                    .lat(getStringValue(node, "lat"))
                    .lng(getStringValue(node, "lng"))
                    .rgstDate(getStringValue(node, "rgstDate"))
                    .build();

            ulsanList.add(ulsanApiRepository.save(ulsan));
        }

        return ulsanList;
    }
    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        return fieldValue != null && !fieldValue.isNull() ? fieldValue.asText() : "-";
    }
    public List<Ulsan> getAllUlsanData() {
        return ulsanApiRepository.findAllByIdLessThan(30);
    }


    public Ulsan getUlsanDataById(Long id) {
        Optional<Ulsan> ulsanOptional = ulsanApiRepository.findById(id);
        return ulsanOptional.orElse(null);
    }

}
