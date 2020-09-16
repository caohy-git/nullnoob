package forum.module.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import forum.module.entity.Article;
import forum.module.mapper.IArticleMapper;
import forum.module.service.IArticleService;
import forum.module.vo.ArticlePageVo;


@Service
public class ArticleServiceImpl extends ServiceImpl<IArticleMapper, Article> implements IArticleService {

	@Autowired
	private IArticleMapper articleMapper;
	@Override
	public List<ArticlePageVo> queryPage(IPage<?> page, Article article) {
		return articleMapper.queryPage(page, article);
	}

}
