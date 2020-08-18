package module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import module.entity.Comment;
import module.service.ICommentService;

@RestController
@RequestMapping("/comment")
@Api(value = "评论管理",tags = "评论管理")
public class CommentController {
	@Autowired
	private ICommentService commentService;

	@RequestMapping("getComment")
	public Comment getComment(String id) {
		Comment comment = commentService.getById(id);
		return comment;
	}
}
