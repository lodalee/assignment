package com.test.assignment.controller;

import com.test.assignment.dto.PostResponseDto;
import com.test.assignment.dto.request.PostRequestDto;
import com.test.assignment.dto.request.RegstrIdRequestDto;
import com.test.assignment.dto.request.UpdatePostRequestDto;
import com.test.assignment.dto.response.exeption.CustomAccessDeniedException;
import com.test.assignment.dto.response.success.MessageDto;
import com.test.assignment.dto.response.success.constant.SuccessMessage;
import com.test.assignment.entity.BoardDef;
import com.test.assignment.service.PostService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    // Create Post
    @PostMapping("/{boardCd}")
    public MessageDto createPost(
            @PathVariable BoardDef.BoardCdType boardCd,
            @Valid @RequestBody PostRequestDto requestDto) {

        postService.createPost(boardCd, requestDto);
        return new MessageDto(SuccessMessage.POST_SUCCESSFUL, HttpStatus.CREATED);
    }

    // Update Post
    @PatchMapping("/{postNo}")
    public MessageDto updatePost(
            @PathVariable Integer postNo,
            @Valid @RequestBody UpdatePostRequestDto requestDto) throws CustomAccessDeniedException {

        postService.updatePost(postNo,requestDto);
        return new MessageDto(SuccessMessage.POST_UPDATE_SUCCESSFUL, HttpStatus.OK);
    }

    // Get a Post
    @GetMapping("/{postNo}")
    public PostResponseDto getAPost(
            @PathVariable Integer postNo){
        return postService.getAPost(postNo);
    }

    // Get Posts
    @GetMapping("")
    public List<PostResponseDto> getPosts(
            @RequestParam("type") BoardDef.BoardCdType boardCd,
            @RequestParam int page){
        int size = 10;
        return postService.getPosts(boardCd, page -1, size);
    }

    // Delete Post
    @DeleteMapping("/{postNo}")
    public MessageDto deletePost(
            @PathVariable Integer postNo,
            @Valid @RequestBody RegstrIdRequestDto regstrId
            ) throws CustomAccessDeniedException {

        postService.deletePost(postNo, regstrId);
        return new MessageDto(SuccessMessage.POST_DELETE_SUCCESFUL, HttpStatus.OK);
    }
}
