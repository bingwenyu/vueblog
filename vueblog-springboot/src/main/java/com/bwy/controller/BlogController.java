package com.bwy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bwy.common.lang.Result;
import com.bwy.entity.Blog;
import com.bwy.mapper.BlogMapper;
import com.bwy.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bwy
 * @since 2020-10-21
 */
@RestController
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = blogMapper.selectPage(page, new QueryWrapper<Blog>().orderByDesc("gmt_create"));

        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogMapper.selectById(id);
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        //Assert.isTrue(false, "公开版不能任意编辑！");
        Blog temp = null;
        if(blog.getId() != null) {
            temp = blogMapper.selectById(blog.getId());
            // 只能编辑自己的文章
            //System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

            //将blog中除了忽略字段之外的字段值赋值给temp
            BeanUtil.copyProperties(blog, temp, "id", "userId", "gmt_create", "status");
            blogMapper.updateById(temp);
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setGmtCreate(LocalDateTime.now());
            temp.setStatus(0);

            //将blog中除了忽略字段之外的字段值赋值给temp
            BeanUtil.copyProperties(blog, temp, "id", "userId", "gmt_create", "status");
            blogMapper.insert(temp);
        }

        return Result.succ(null);
    }


}
