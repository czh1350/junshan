package com.junshan.item.service.impl;

import java.util.List;
import com.junshan.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.junshan.item.mapper.JsContentMapper;
import com.junshan.item.domain.JsContent;
import com.junshan.item.service.IJsContentService;

/**
 * 文本Service业务层处理
 *
 * @author czh
 * @date 2021-04-30
 */
@Service
@AllArgsConstructor
public class JsContentServiceImpl implements IJsContentService
{
    private JsContentMapper jsContentMapper;

    /**
     * 查询文本
     *
     * @param id 文本ID
     * @return 文本
     */
    @Override
    public JsContent selectJsContentById(Long id)
    {
        return jsContentMapper.selectJsContentById(id);
    }

    /**
     * 查询文本列表
     *
     * @param jsContent 文本
     * @return 文本
     */
    @Override
    public List<JsContent> selectJsContentList(JsContent jsContent)
    {
        return jsContentMapper.selectJsContentList(jsContent);
    }

    /**
     * 新增文本
     *
     * @param jsContent 文本
     * @return 结果
     */
    @Override
    public int insertJsContent(JsContent jsContent)
    {
        jsContent.setCreateTime(DateUtils.getNowDate());
        return jsContentMapper.insertJsContent(jsContent);
    }

    /**
     * 修改文本
     *
     * @param jsContent 文本
     * @return 结果
     */
    @Override
    public int updateJsContent(JsContent jsContent)
    {
        return jsContentMapper.updateJsContent(jsContent);
    }

    /**
     * 批量删除文本
     *
     * @param ids 需要删除的文本ID
     * @return 结果
     */
    @Override
    public int deleteJsContentByIds(Long[] ids)
    {
        return jsContentMapper.deleteJsContentByIds(ids);
    }

    /**
     * 删除文本信息
     *
     * @param id 文本ID
     * @return 结果
     */
    @Override
    public int deleteJsContentById(Long id)
    {
        return jsContentMapper.deleteJsContentById(id);
    }
}
