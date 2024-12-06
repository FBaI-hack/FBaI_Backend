package bigtech.fbai.post.application;

import bigtech.fbai.post.application.dto.response.GetCommentResponseDto;
import bigtech.fbai.post.dao.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Page<GetCommentResponseDto> findComments(Long memberId, Pageable pageable) {
        return commentRepository.findAllByMemberId(memberId, pageable).map(
            comment -> new GetCommentResponseDto(comment.getCommentId(), comment.getContent(),
                comment.getPost().getPostId(), comment.getCreatedAt().toLocalDate()));
    }
}
