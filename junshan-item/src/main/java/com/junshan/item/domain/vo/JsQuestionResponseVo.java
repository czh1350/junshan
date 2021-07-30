package com.junshan.item.domain.vo;

import com.junshan.item.domain.JsQuestion;
import lombok.Data;

/**
 * 题目返回对象
 */
@Data
public class JsQuestionResponseVo extends JsQuestion {
    /** 题干 */
    private String shortTitle;
}
