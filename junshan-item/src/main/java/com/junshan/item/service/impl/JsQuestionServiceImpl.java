package com.junshan.item.service.impl;

import java.util.List;

import com.junshan.common.utils.DateUtils;
import com.junshan.common.utils.HtmlUtil;
import com.junshan.common.utils.JsonUtil;
import com.junshan.common.utils.SecurityUtils;
import com.junshan.item.domain.JsContent;
import com.junshan.item.domain.vo.JsQuestionResponseVo;
import com.junshan.item.domain.vo.JsQuestionVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.junshan.item.mapper.JsQuestionMapper;
import com.junshan.item.domain.JsQuestion;
import com.junshan.item.service.IJsQuestionService;
import org.springframework.util.CollectionUtils;

/**
 * 题目Service业务层处理
 *
 * @author czh
 * @date 2021-04-30
 */
@Service
@AllArgsConstructor
public class JsQuestionServiceImpl implements IJsQuestionService {
    private JsQuestionMapper jsQuestionMapper;

    /**
     * 查询题目
     *
     * @param id 题目ID
     * @return 题目
     */
    @Override
    public JsQuestionResponseVo selectJsQuestionById(Long id) {
        JsQuestionResponseVo jsQuestionResponseVo = jsQuestionMapper.selectJsQuestionById(id);

        return jsQuestionMapper.selectJsQuestionById(id);
    }

    /**
     * 查询题目列表
     *
     * @param jsQuestion 题目
     * @return 题目
     */
    @Override
    public List<JsQuestionResponseVo> selectJsQuestionList(JsQuestion jsQuestion) {
        List<JsQuestionResponseVo> jsQuestionList = jsQuestionMapper.selectJsQuestionList(jsQuestion);
        if (!CollectionUtils.isEmpty(jsQuestionList)) {
            jsQuestionList.forEach(item -> {
                JsContent jsContent = item.getJsContent();
                if (jsContent != null) {
                    //封装内容JSON到对象
                    JsQuestionVo jsQuestionVo = JsonUtil.toJsonObject(jsContent.getContent(), JsQuestionVo.class);
                    String titleContent = HtmlUtil.clear(jsQuestionVo.getTitleContent());
                    item.setShortTitle(titleContent);
                }
            });
        }
        return jsQuestionList;
    }

    /**
     * 新增题目
     *
     * @param jsQuestion 题目
     * @return 结果
     */
    @Override
    public int insertJsQuestion(JsQuestion jsQuestion) {
        jsQuestion.setCreateTime(DateUtils.getNowDate());
        jsQuestion.setCreateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        jsQuestion.setUpdateTime(DateUtils.getNowDate());
        jsQuestion.setUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return jsQuestionMapper.insertJsQuestion(jsQuestion);
    }

    /**
     * 修改题目
     *
     * @param jsQuestion 题目
     * @return 结果
     */
    @Override
    public int updateJsQuestion(JsQuestion jsQuestion) {
        jsQuestion.setUpdateTime(DateUtils.getNowDate());
        jsQuestion.setUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        return jsQuestionMapper.updateJsQuestion(jsQuestion);
    }

    /**
     * 批量删除题目
     *
     * @param ids 需要删除的题目ID
     * @return 结果
     */
    @Override
    public int deleteJsQuestionByIds(Long[] ids) {
        return jsQuestionMapper.deleteJsQuestionByIds(ids);
    }

    /**
     * 删除题目信息
     *
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteJsQuestionById(Long id) {
        return jsQuestionMapper.deleteJsQuestionById(id);
    }
}
