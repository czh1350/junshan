package com.junshan.item.service;

import java.util.List;
import com.junshan.item.domain.JsQuestion;
import com.junshan.item.domain.vo.JsQuestionResponseVo;

/**
 * 题目Service接口
 *
 * @author czh
 * @date 2021-04-30
 */
public interface IJsQuestionService
{
    /**
     * 查询题目
     *
     * @param id 题目ID
     * @return 题目
     */
    public JsQuestionResponseVo selectJsQuestionById(Long id);

    /**
     * 查询题目列表
     *
     * @param jsQuestion 题目
     * @return 题目集合
     */
    public List<JsQuestionResponseVo> selectJsQuestionList(JsQuestion jsQuestion);

    /**
     * 新增题目
     *
     * @param jsQuestion 题目
     * @return 结果
     */
    public int insertJsQuestion(JsQuestion jsQuestion);

    /**
     * 修改题目
     *
     * @param jsQuestion 题目
     * @return 结果
     */
    public int updateJsQuestion(JsQuestion jsQuestion);

    /**
     * 批量删除题目
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    public int deleteJsQuestionByIds(Long[] ids);

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    public int deleteJsQuestionById(Long id);
}
