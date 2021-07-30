package com.junshan.item.domain;

import com.junshan.common.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.Size;

import com.junshan.common.core.domain.BaseEntity;

/**
 * 课程对象 js_subject
 *
 * @author czh
 * @date 2021-07-01
 */
@Data
public class JsSubject extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    @Size(min = 0, max = 64, message = "课程名称长度不能超过64个字符")
    private String name;

    /**
     * 课程类型
     */
    @Excel(name = "课程类型")
    private Long subjectType;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;


}
