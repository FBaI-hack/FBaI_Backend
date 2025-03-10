package bigtech.fbai.post.application;

import bigtech.fbai.common.dto.CommonSuccessDto;
import bigtech.fbai.common.exception.CommonException;
import bigtech.fbai.common.exception.ErrorCode;
import bigtech.fbai.member.app.MemberService;
import bigtech.fbai.member.dao.entity.Member;
import bigtech.fbai.post.application.dto.request.CreateCommentRequestDto;
import bigtech.fbai.post.application.dto.request.UpdateCommentRequestDto;
import bigtech.fbai.post.application.dto.response.GetCommentResponseDto;
import bigtech.fbai.post.dao.CommentRepository;
import bigtech.fbai.post.dao.entity.Comment;
import bigtech.fbai.post.dao.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    public Comment finidParentComment(Long commentId) {
        if (commentId == null) {
            return null;
        }
        return findComment(commentId);
    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMENT));
    }

    public Page<GetCommentResponseDto> findComments(Long memberId, Pageable pageable) {
        return commentRepository.findAllByMemberId(memberId, pageable).map(
            comment -> new GetCommentResponseDto(comment.getCommentId(), comment.getContent(),
                comment.getPost().getPostId(), comment.getCreatedAt().toLocalDate()));
    }

    @Transactional
    public CommonSuccessDto createComment(Long memberId, CreateCommentRequestDto dto) {
        Post post = postService.findPost(dto.postId());
        Member member = memberService.findMember(memberId);
        Comment parentComment = finidParentComment(dto.parentCommentId());

        Comment comment = Comment.create(member, post, dto.content(), parentComment);
        commentRepository.save(comment);

        return CommonSuccessDto.success();
    }

    @Transactional
    public CommonSuccessDto updateComment(Long memberId, Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = findComment(commentId);
        comment.validateMember(memberId);

        comment.update(dto.content());

        return CommonSuccessDto.success();
    }

    @Transactional
    public CommonSuccessDto deleteComment(Long memberId, Long commentId) {
        Comment comment = findComment(commentId);
        comment.validateMember(memberId);

        commentRepository.delete(comment);

        return CommonSuccessDto.success();
    }
}
