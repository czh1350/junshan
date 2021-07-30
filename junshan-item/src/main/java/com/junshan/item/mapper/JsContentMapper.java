package com.junshan.item.mapper;

import com.junshan.item.domain.JsContent;

import java.util.List;

/**
 * 文本Mapper接口
 *
 * @author czh
 * @date 2021-04-30
 */
public interface JsContentMapper
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
     * 删除文本
     *
     * @param id 文本ID
     * @return 结果
     */
    public int deleteJsContentById(Long id);

    /**
     * 批量删除文本
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJsContentByIds(Long[] ids);
}
