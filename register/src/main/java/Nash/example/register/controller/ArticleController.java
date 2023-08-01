package Nash.example.register.controller;

import Nash.example.register.model.Article;
import Nash.example.register.result.BaseResult;
import Nash.example.register.result.DataResult;
import Nash.example.register.util.HttpStatusMap;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
@Api(tags="文章接口，提供文章的新建、修改、查询和删除操作")
public class ArticleController {
    static List<Article> articles = new ArrayList<>();
    static {
        articles.add(new Article(1, "《Spring Boot无难事》"));
        articles.add(new Article(2, "《Java无难事》"));
        articles.add(new Article(3, "《Vue.js 3.0从入门到实战》"));
    }


    // 保存新的文章
    @PostMapping
    @ApiOperation("保存新的文章")
    @ApiResponse(code = 200, message = "保存成功")
    public ResponseEntity<BaseResult> saveArticle(@RequestBody Article article){
        articles.add(article);
        System.out.println(articles);
        BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "保存成功");
        return ResponseEntity.ok(result);
    }

    // 根据ID查找文章
    @GetMapping("/{id}")
    @ApiOperation("根据文章ID获取单篇文章")
    @ApiImplicitParam(name = "id", value="文章ID")
    @ApiResponses({@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "参数不合法")})
    public ResponseEntity<BaseResult> getArticleById(@PathVariable Integer id){
        Optional<Article> opArticle =  articles.stream()
                .filter(art -> art.getId() == id).findFirst();

        try {
            Article article = opArticle.get();
            DataResult result = new DataResult();
            result.setCode(HttpStatusMap.get(HttpStatus.OK));
            result.setMsg("成功");
            result.setData(article);
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e){
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "参数不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    // 返回所有文章数据
    @GetMapping
    @ApiOperation("获取所有文章")
    @ApiResponse(code = 200, message = "成功")
    public ResponseEntity<DataResult<List<Article>>> getAllArticles(){
        DataResult result = new DataResult();
        result.setCode(HttpStatusMap.get(HttpStatus.OK));
        result.setMsg("成功");
        result.setData(articles);
        return ResponseEntity.ok(result);
    }

    // 修改文章
    @PutMapping
    @ApiOperation("修改文章")
    @ApiResponses({@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "参数不合法")})
    public ResponseEntity<BaseResult> updateArticle(@RequestBody Article article){
        Optional<Article> opArticle = articles.stream()
                .filter(art -> art.getId() == article.getId())
                .findFirst();

        try {
            Article updatedArticle = opArticle.get();
            BeanUtils.copyProperties(article, updatedArticle);
            System.out.println(articles);
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "修改成功");
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e){
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "参数不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    // 根据ID删除文章
    @DeleteMapping("/{id}")
    @ApiOperation("根据文章ID删除单篇文章")
    @ApiImplicitParam(name = "id", value="文章ID")
    @ApiResponses({@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "参数不合法")})
    public ResponseEntity<BaseResult> deleteArticle(@PathVariable Integer id){
        Optional<Article> opArticle = articles.stream()
                .filter(art -> art.getId() == id).findFirst();
        try{
            Article article = opArticle.get();
            articles.remove(article);
            System.out.println(articles);
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "删除成功");
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e) {
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "参数不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}

