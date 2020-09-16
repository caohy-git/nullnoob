package forum.module.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import forum.module.entity.Article;
import forum.module.vo.ArticlePageVo;

public interface IArticleService extends IService<Article>{

	List<ArticlePageVo> queryPage(IPage<?> page, Article article);

}
