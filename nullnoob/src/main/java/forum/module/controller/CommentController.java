package forum.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import forum.module.common.constant.Rp;
import forum.module.entity.Comment;
import forum.module.params.CommentParam;
import forum.module.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	
	@ApiOperation("添加评论")
	@PostMapping("/add")
	@ResponseBody
	public Rp<String> add(HttpServletRequest request, CommentParam commentParam){
		return null;
	}
}
