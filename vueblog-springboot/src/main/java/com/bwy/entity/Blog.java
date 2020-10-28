package com.bwy.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author bwy
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_blog")
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotBlank(message = "摘要不能为空")
    @ApiModelProperty(value = "摘要")
    private String description;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "乐观锁版本号")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
