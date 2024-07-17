package by.vasyabylba.crud.onetomany.service.impl;

import by.vasyabylba.crud.onetomany.dto.PostRequest;
import by.vasyabylba.crud.onetomany.dto.PostResponse;
import by.vasyabylba.crud.onetomany.exception.ResourceNotFoundException;
import by.vasyabylba.crud.onetomany.mapper.PostMapper;
import by.vasyabylba.crud.onetomany.model.Post;
import by.vasyabylba.crud.onetomany.model.User;
import by.vasyabylba.crud.onetomany.repository.PostRepository;
import by.vasyabylba.crud.onetomany.repository.UserRepository;
import by.vasyabylba.crud.onetomany.service.PostService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<PostResponse> getList() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    @Override
    public List<PostResponse> getListByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(userId)));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    @Override
    public PostResponse getOne(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postMapper.toPostResponse(postOptional.orElseThrow(() ->
                new ResourceNotFoundException("Post with id `%s` not found".formatted(id))));
    }

    @Override
    public List<PostResponse> getMany(List<Long> ids) {
        List<Post> posts = postRepository.findAllById(ids);
        return posts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    @Override
    public PostResponse create(Long userId, PostRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(userId)));
        Post post = postMapper.toPost(dto);
        post.setUser(user);
        Post resultPost = postRepository.save(post);
        return postMapper.toPostResponse(resultPost);
    }

    @Override
    public PostResponse put(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post with id `%s` not found".formatted(id)));

        postMapper.updateWithNull(postRequest, post);

        Post resultPost = postRepository.save(post);
        return postMapper.toPostResponse(resultPost);
    }

    @Override
    public List<Long> putMany(List<Long> ids, PostRequest postRequest) {
        Collection<Post> posts = postRepository.findAllById(ids);

        for (Post post : posts) {
            postMapper.updateWithNull(postRequest, post);
        }

        List<Post> resultPosts = postRepository.saveAll(posts);
        return resultPosts.stream()
                .map(Post::getId)
                .toList();
    }

    @Override
    public PostResponse patch(Long id, JsonNode patchNode) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post with id `%s` not found".formatted(id)));

        PostRequest postRequest = postMapper.toPostRequest(post);
        objectMapper.readerForUpdating(postRequest).readValue(patchNode);
        postMapper.updateWithNull(postRequest, post);

        Post resultPost = postRepository.save(post);
        return postMapper.toPostResponse(resultPost);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Post> posts = postRepository.findAllById(ids);

        for (Post post : posts) {
            PostRequest postRequest = postMapper.toPostRequest(post);
            objectMapper.readerForUpdating(postRequest).readValue(patchNode);
            postMapper.updateWithNull(postRequest, post);
        }

        List<Post> resultPosts = postRepository.saveAll(posts);
        return resultPosts.stream()
                .map(Post::getId)
                .toList();
    }

    @Override
    public void delete(Long id) {
        postRepository.findById(id).ifPresent(postRepository::delete);
    }

    @Override
    public void deleteMany(List<Long> ids) {
        postRepository.deleteAllById(ids);
    }

    @Override
    public void deleteByUser(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(userId)));
        postRepository.deleteById(postId);
    }

    @Override
    public void deleteAllByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id `%s` not found".formatted(userId)));
        postRepository.deleteAllByUser(user);
    }
}
