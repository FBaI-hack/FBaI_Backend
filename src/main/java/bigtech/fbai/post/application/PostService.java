package bigtech.fbai.post.application;

import bigtech.fbai.post.application.dto.response.GetPagedPostsResponseDto;
import bigtech.fbai.post.dao.PostRepository;
import bigtech.fbai.post.dao.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public GetPagedPostsResponseDto getPosts(String category, int page) {
        List<Post> posts = postRepository.findPostsByCategory(category,page);
        return GetPagedPostsResponseDto.from(posts);
    }
}
