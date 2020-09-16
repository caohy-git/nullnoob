package forum.module.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import forum.module.entity.Comment;
import forum.module.mapper.ICommentMapper;
import forum.module.service.ICommentService;
@Service
public class CommentServiceImpl extends ServiceImpl<ICommentMapper, Comment> implements ICommentService{

}
