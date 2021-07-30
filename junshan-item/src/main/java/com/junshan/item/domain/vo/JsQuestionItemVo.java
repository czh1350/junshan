package com.junshan.item.domain.vo;

import lombok.Data;

/**
 * 试题选项对象
 * @author czh
 * @Date 2021年7月16日
 */
@Data
public class JsQuestionItemVo {
    /** 选项 */
    private String prefix;
    /** 内容 */
    private String content;
    /** 分数 */
    private Integer score;

    private String itemUuid;
}
