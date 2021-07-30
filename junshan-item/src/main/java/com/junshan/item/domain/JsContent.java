package com.junshan.item.domain;

import lombok.Data;

import com.junshan.common.core.domain.BaseEntity;

/**
 * 文本对象 js_content
 *
 * @author czh
 * @date 2021-07-01
 */
@Data
public class JsContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 内容
     */
    private String content;


}
