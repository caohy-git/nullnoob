package module.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import module.entity.Comment;
import module.mapper.ICommentMapper;
import module.service.ICommentService;
@Service
public class CommentServiceImpl extends ServiceImpl<ICommentMapper, Comment> implements ICommentService{

}
