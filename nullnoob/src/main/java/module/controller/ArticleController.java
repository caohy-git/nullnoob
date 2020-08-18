package module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import module.entity.Article;
import module.service.IArticleService;

@RestController
@RequestMapping("/article")
@Api(value = "文章管理",tags = "文章管理")
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	@RequestMapping("getArticle")
	public Article getArticle(String id) {
		Article article = articleService.getById(id);
		return article;
	}
}
