package com.junshan.item.service;

import java.util.List;
import com.junshan.item.domain.JsContent;

/**
 * 文本Service接口
 *
 * @author czh
 * @date 2021-04-30
 */
public interface IJsContentService
{
    /**
     * 查询文本
     *
     * @param id 文本ID
     * @return 文本
     */
    public JsContent selectJsContentById(Long id);

    /**
     * 查询文本列表
     *
     * @param jsContent 文本
     * @return 文本集合
     */
    public List<JsContent> selectJsContentList(JsContent jsContent);

    /**
     * 新增文本
     *
     * @param jsContent 文本
     * @return 结果
     */
    public int insertJsContent(JsContent jsContent);

    /**
     * 修改文本
     *
     * @param jsContent 文本
     * @return 结果
     */
    public int updateJsContent(JsContent jsContent);

    /**
     * 批量删除文本
     *
     * @param ids 需要删除的文本ID
     * @return 结果
     */
    public int deleteJsContentByIds(Long[] ids);

    /**
     * 删除文本信息
     *
     * @param id 文本ID
     * @return 结果
     */
    public int deleteJsContentById(Long id);
}
