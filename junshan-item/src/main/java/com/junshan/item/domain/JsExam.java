package com.junshan.item.domain;

import com.junshan.common.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.Size;

import com.junshan.common.core.domain.BaseEntity;

/**
 * 试卷对象 js_exam
 *
 * @author czh
 * @date 2021-07-01
 */
@Data
public class JsExam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 试卷名称
     */
    @Excel(name = "试卷名称")
    @Size(min = 0, max = 255, message = "试卷名称长度不能超过255个字符")
    private String name;

    /**
     * 课程id
     */
    private Long subjectId;

    /**
     * 试卷类型
     */
    @Excel(name = "试卷类型")
    private Long paperType;

    /**
     * 分数
     */
    @Excel(name = "分数")
    private Integer score;

    /**
     * 试题数量
     */
    @Excel(name = "试题数量")
    private Integer questionCount;

    /**
     * 建议时长(分钟)
     */
    @Excel(name = "建议时长(分钟)")
    private Integer suggestTime;

    /**
     * 试卷框架 内容为JSON
     */
    private Long textQuestionId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;


}
