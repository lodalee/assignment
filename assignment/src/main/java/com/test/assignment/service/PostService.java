package com.test.assignment.service;

import com.test.assignment.dto.PostResponseDto;
import com.test.assignment.dto.request.PostRequestDto;
import com.test.assignment.dto.request.RegstrIdRequestDto;
import com.test.assignment.dto.request.UpdatePostRequestDto;
import com.test.assignment.dto.response.exeption.CustomAccessDeniedException;
import com.test.assignment.dto.response.exeption.CustomEntityNotFoundException;
import com.test.assignment.dto.response.exeption.InvalidInputException;
import com.test.assignment.dto.response.exeption.constant.ExceptionMessage;
import com.test.assignment.entity.BoardDef;
import com.test.assignment.entity.Post;
import com.test.assignment.entity.PostTag;
import com.test.assignment.entity.Tag;
import com.test.assignment.repository.BoardDefRepository;
import com.test.assignment.repository.PostRepository;
import com.test.assignment.repository.PostTagRepository;
import com.test.assignment.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    // Create Post
    public void createPost(BoardDef.BoardCdType boardCd, PostRequestDto requestDto) {
        BoardDef boardDef = boardDefRepository.findByBoardCd(boardCd)
                .orElseThrow(() -> new CustomEntityNotFoundException(ExceptionMessage.BOARD_CD_NOT_FOUND));

        //중복된 문자열 검사
        checkRepeatedCharacters(requestDto.getPostSj());
        checkRepeatedCharacters(requestDto.getRegstrId());
        checkRepeatedCharacters(requestDto.getPostCn());

        //게시물 생성
        Post post = new Post(boardDef, requestDto);

        //게시물 저장
        post = postRepository.save(post);

        //태그 생성 및 연결
        createTagsAndLinkToPost(requestDto.getTags(), post);

    }

    // Update Post
    @Transactional
    public void updatePost(Integer postNo, UpdatePostRequestDto requestDto) throws CustomAccessDeniedException {
        Post post = findPostById(postNo);

        checkRegstrId(requestDto.getRegstrId(), post);

        // 제목이 null이 아닌 경우에만 수정
        if (requestDto.getPostSj() != null) {
            checkRepeatedCharacters(requestDto.getPostSj());
            post.setPostSj(requestDto.getPostSj());
        }

        // 내용이 null이 아닌 경우에만 수정
        if (requestDto.getPostCn() != null) {
            checkRepeatedCharacters(requestDto.getPostCn());
            post.setPostCn(requestDto.getPostCn());
        }

        //태그들이 null이 아니라면
        if (requestDto.getTags() != null) {
            //새로운 태그
            List<String> newTagNames = requestDto.getTags();

            // 기존의 태그들
            List<PostTag> postTags = post.getPostTags();
            List<String> oldTagNames = postTags.stream()
                    .map(postTag -> postTag.getTag().getTag())
                    .collect(Collectors.toList());

            //태그가 수정되었는지 확인 -> 기존의 postTag 관계 삭제
            if (!newTagNames.equals(oldTagNames)) {
                postTagRepository.deleteAll(postTags);
                createTagsAndLinkToPost(requestDto.getTags(), post);
            }
        }
    }

    private void createTagsAndLinkToPost(List<String> tagNames, Post post) {
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByTag(tagName);
            if (tag == null) {
                tag = tagRepository.save(new Tag(tagName));
            }
            PostTag postTag = new PostTag();
            postTag.setPost(post);
            postTag.setTag(tag);
            postTagRepository.save(postTag); // PostTag 저장
        }
    }

    // Get a Post
    public PostResponseDto getAPost(Integer postNo) {
        Post post = findPostById(postNo);
        return new PostResponseDto(post);
    }

    // Get Posts
    public List<PostResponseDto> getPosts(BoardDef.BoardCdType boardCd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDt"));

        if (boardCd.equals(BoardDef.BoardCdType.ALL)) {
            Page<Post> posts = postRepository.findAll(pageable);

            return posts.stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
        } else {
            Page<Post> postsType = postRepository.findAllByBoardDef_BoardCd(boardCd, pageable);

            return postsType.stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    // Delete Post
    public void deletePost(Integer postNo, RegstrIdRequestDto regstrId) throws CustomAccessDeniedException {
        Post post = findPostById(postNo);
        checkRegstrId(regstrId.toString(), post);
        postRepository.delete(post);
    }

    //find PostId
    public Post findPostById(Integer postNo) {
        return postRepository.findById(postNo)
                .orElseThrow(() -> new CustomEntityNotFoundException(ExceptionMessage.POST_NOT_FOUND));
    }

    //check the regstrId
    public void checkRegstrId(String regstrId, Post post) throws CustomAccessDeniedException {
        if (!regstrId.equals(post.getRegstrId())) {
            throw new CustomAccessDeniedException(ExceptionMessage.PERMISSION_DENIED);
        }
    }

    public void checkRepeatedCharacters(String input) {
        Pattern pattern = Pattern.compile("(.)\\1{4,}");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            throw new InvalidInputException(ExceptionMessage.DUPLICATE_PATTERN);
        }
    }
}
