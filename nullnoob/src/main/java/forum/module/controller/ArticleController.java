package forum.module.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import forum.module.common.constant.Rp;
import forum.module.common.constant.RpEnum;
import forum.module.common.exception.BizException;
import forum.module.common.page.PageRP;
import forum.module.common.page.PageRQ;
import forum.module.common.page.PageUtil;
import forum.module.common.util.StringUtil;
import forum.module.entity.Article;
import forum.module.entity.User;
import forum.module.params.ArticlePageParams;
import forum.module.params.ArticleParams;
import forum.module.service.IArticleService;
import forum.module.vo.ArticlePageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
@Api(value = "文章管理",tags = "文章管理")
public class ArticleController {

	@Autowired
	private IArticleService articleService;
	
	//跳转新增页面
	@RequestMapping("/addArticleView")
	@ApiOperation("跳转新增页面")
	public ModelAndView addArticleView() {
		return new ModelAndView("article/add.html");
	}
	@GetMapping("/getArticle")
	@ApiOperation("查询文章")
	public Rp<Article> getArticle(HttpServletRequest request,String id){
		if(StringUtil.isEmpty(id)) {
			throw new BizException("缺少必要参数");
		}
		Article article = articleService.getById(id);
		return Rp.buildSuccess(article);
	}
	
	@PostMapping("/add")
	@ApiOperation("新增")
	public Rp<String> add(HttpServletRequest request,ArticleParams articleParams){
		User user = (User) request.getSession().getAttribute("USER");
		if(user == null) {
			throw new BizException(RpEnum.NO_AUTHEN);
		}
		if(articleParams == null) {
			throw new BizException("缺少必要参数");
		}
		if(StringUtil.isEmpty(articleParams.getTitle())) {
			throw new BizException("文章标题不能为空");
		}
		if(articleParams.getTitle().length() > 50) {
			throw new BizException("文章标题长度不能超过50个字符");
		}
		if(StringUtil.isEmpty(articleParams.getTheme())) {
			throw new BizException("文章主题不能为空");
		}
		if(articleParams.getTheme().length() > 200) {
			throw new BizException("文章主题不能超过200个字符");
		}
		if(StringUtil.isEmpty(articleParams.getType())) {
			throw new BizException("文章类型不能为空");
		}
		if(StringUtil.isEmpty(articleParams.getContent())) {
			throw new BizException("文章内容不能为空");
		}
		if(StringUtil.isEmpty(articleParams.getStatus())) {
			throw new BizException("文章状态不能为空");
		}
		
		Article article = new Article();
		BeanUtils.copyProperties(articleParams, article);
		article.setId(StringUtil.generateUUID());
		
		//系统状态
		article.setCheckStatus(0);
		article.setIsDelete(0);
		article.setThumbUpNum(0);
		article.setCreateUser(user.getId());
		article.setCreateTime(new Date());
		if(article.getStatus().equals(1)) {
			article.setPublishTime(new Date());
		}
		articleService.save(article);
		return Rp.buildSuccess();
	}
	
	@ApiOperation("文章列表")
	@GetMapping("/page")
	@ResponseBody 
	public Rp<PageRP<ArticlePageVo>> page(HttpServletRequest request, PageRQ pageRQ, ArticlePageParams param) throws JsonProcessingException{
		IPage<ArticlePageVo> iPage = PageUtil.toMybatisPage(pageRQ);
		Article article = new Article();
		BeanUtils.copyProperties(param, article);
		
		List<ArticlePageVo> list = articleService.queryPage(iPage, article);
		System.out.println(new ObjectMapper().writeValueAsString(iPage));
		PageRP<ArticlePageVo> pageRP = PageUtil.toPage(iPage);
		pageRP.setList(list);
		return Rp.buildSuccess(pageRP);
	}
	
	@ApiOperation("文章详情")
	@GetMapping("/articleDetail")
	@ResponseBody
	public Rp<Article> articleDetail(String articleId){
		if(StringUtils.isEmpty(articleId)) {
			throw new BizException(RpEnum.NO_PARAMETER);
		}
		Article article = articleService.getById(articleId);
		//TODO 评论
		
		return Rp.buildSuccess(article);
	}
}
