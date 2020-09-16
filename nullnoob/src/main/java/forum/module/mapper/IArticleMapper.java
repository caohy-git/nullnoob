package forum.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import forum.module.entity.Article;
import forum.module.vo.ArticlePageVo;

public interface IArticleMapper extends BaseMapper<Article>{

	List<ArticlePageVo> queryPage(IPage<?> iPage,@Param("article") Article article);

}
