/*
package com.ll.traveler.domain.review.post.service;

import com.ll.traveler.domain.review.post.dto.GyeonggiPostDTO;
import com.ll.traveler.domain.review.post.entity.GyeonggiPost;
import com.ll.traveler.domain.review.post.repository.GyeonggiPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GyeonggiPostService {
    private final GyeonggiPostRepository gyeonggiPostRepository;

    // 저장된 게시물 목록을 가져오는 메서드
    public List<GyeonggiPostDTO> getSavedPosts(String username) {
        List<GyeonggiPost> savedPosts = gyeonggiPostRepository.findByUsername(username);
        return savedPosts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 게시물을 저장하는 메서드
    public void saveGyeonggiPost(GyeonggiPostDTO gyeonggiPostDTO, String username) {
        GyeonggiPost gyeonggiPost = new GyeonggiPost();
        // DTO에서 엔티티로 매핑 작업 수행
        mapToEntity(gyeonggiPostDTO, gyeonggiPost);
        // 유저네임 설정
        gyeonggiPost.setUsername(username);
        // 저장
        gyeonggiPostRepository.save(gyeonggiPost);
    }

    // DTO를 엔티티로 매핑하는 메서드
    private void mapToEntity(GyeonggiPostDTO dto, GyeonggiPost entity) {
        // 필요한 매핑 작업 수행
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        // 나머지 필드에 대해서도 매핑 작업 수행
    }

    // 엔티티를 DTO로 매핑하는 메서드
    private GyeonggiPostDTO mapToDTO(GyeonggiPost entity) {
        GyeonggiPostDTO dto = new GyeonggiPostDTO();
        // 필요한 매핑 작업 수행
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        // 나머지 필드에 대해서도 매핑 작업 수행
        return dto;
    }
}*/
