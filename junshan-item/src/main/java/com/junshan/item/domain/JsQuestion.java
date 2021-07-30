package com.junshan.item.domain;

import com.junshan.common.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.Size;

import com.junshan.common.core.domain.BaseEntity;

/**
 * 题目对象 js_question
 *
 * @author czh
 * @date 2021-07-01
 */
@Data
public class JsQuestion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 问题类型
     */
    @Excel(name = "问题类型")
    private Long questionType;

    /**
     * 课程id
     */
    private Long subjectId;

    /**
     * 题干总分
     */
    @Excel(name = "题干总分")
    private Integer score;

    /**
     * 题目难度
     */
    @Excel(name = "题目难度")
    private Long difficult;

    /**
     * 正确答案
     */
    private String correct;

    /**
     * 题目 填空、 题干、解析、答案等信息
     */
    private Long textContentId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @Size(min = 0, max = 1, message = "状态长度不能超过1个字符")
    private String status;

    /** 课程对象 */
    private JsSubject jsSubject;

    /** 题干对象 */
    private JsContent jsContent;


}
