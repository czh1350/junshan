package com.junshan.item.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 试题详情对象
 * @author czh
 * @date 2021年7月16日
 */
@Data
public class JsQuestionVo {
    /** 题干 */
    private String titleContent;
    /** 解析 */
    private String analyze;
    /** 选项集合 */
    private List<JsQuestionItemVo> questionItems;
    /** 正确答案 */
    private String correct;
}
