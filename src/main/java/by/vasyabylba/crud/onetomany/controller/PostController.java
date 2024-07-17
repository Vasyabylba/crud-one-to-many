package by.vasyabylba.crud.onetomany.controller;

import by.vasyabylba.crud.onetomany.dto.PostRequest;
import by.vasyabylba.crud.onetomany.dto.PostResponse;
import by.vasyabylba.crud.onetomany.service.PostService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{id}")
    public PostResponse getOne(@PathVariable Long id) {
        return postService.getOne(id);
    }

    @GetMapping("/posts/by-ids")
    public List<PostResponse> getMany(@RequestParam("id") List<Long> ids) {
        return postService.getMany(ids);
    }

    @GetMapping("/posts")
    public List<PostResponse> getListByUser() {
        return postService.getList();
    }

    @GetMapping("/users/{userId}/posts")
    public List<PostResponse> getListByUser(@PathVariable Long userId) {
        return postService.getListByUser(userId);
    }

    @PostMapping("/users/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@PathVariable Long userId, @RequestBody PostRequest dto) {
        return postService.create(userId, dto);
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PostResponse put(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        return postService.put(id, postRequest);
    }

    @PutMapping("/posts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Long> putMany(@RequestParam("id") List<Long> ids, @RequestBody PostRequest postRequest) {
        return postService.putMany(ids, postRequest);
    }

    @PatchMapping("/posts/{id}")
    public PostResponse patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return postService.patch(id, patchNode);
    }

    @PatchMapping("/posts")
    public List<Long> patchMany(@RequestParam("id") List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return postService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @DeleteMapping("/posts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMany(@RequestParam("id") List<Long> ids) {
        postService.deleteMany(ids);
    }

    @DeleteMapping("/users/{userId}/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUser(@PathVariable Long userId, @PathVariable Long id) {
        postService.deleteByUser(userId, id);
    }

    @DeleteMapping("/users/{userId}/posts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteListByUser(@PathVariable Long userId) {
        postService.deleteAllByUser(userId);
    }
}
