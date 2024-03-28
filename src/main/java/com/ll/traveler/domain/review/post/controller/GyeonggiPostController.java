/*
package com.ll.traveler.domain.review.post.controller;

import com.ll.traveler.domain.review.post.dto.GyeonggiPostDTO;
import com.ll.traveler.domain.review.post.service.GyeonggiPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class GyeonggiPostController {
    private final GyeonggiPostService gyeonggiPostService;

    // 마이페이지에서 저장된 게시물 목록을 보여주는 엔드포인트
    @GetMapping("/savedPosts")
    public ResponseEntity<List<GyeonggiPostDTO>> getSavedPosts(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        List<GyeonggiPostDTO> savedPosts = gyeonggiPostService.getSavedPosts(username);

        return ResponseEntity.ok(savedPosts);
    }

    // 게시물을 저장하는 엔드포인트
    @PostMapping("/saveGyeonggi")
    public ResponseEntity<String> saveGyeonggiPost(@RequestBody GyeonggiPostDTO gyeonggiPostDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // 게시물 저장 서비스를 통해 게시물을 저장합니다.
        gyeonggiPostService.saveGyeonggiPost(gyeonggiPostDTO, username);

        // 저장이 완료되면 성공 응답을 반환합니다.
        return ResponseEntity.ok("게시물이 저장되었습니다.");
    }
}*/
