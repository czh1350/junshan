package com.junshan.item.service.impl;

import java.util.List;
import com.junshan.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.junshan.item.mapper.JsExamMapper;
import com.junshan.item.domain.JsExam;
import com.junshan.item.service.IJsExamService;

/**
 * 试卷Service业务层处理
 *
 * @author czh
 * @date 2021-04-30
 */
@Service
@AllArgsConstructor
public class JsExamServiceImpl implements IJsExamService
{
    private JsExamMapper jsExamMapper;

    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    @Override
    public JsExam selectJsExamById(Long id)
    {
        return jsExamMapper.selectJsExamById(id);
    }

    /**
     * 查询试卷列表
     *
     * @param jsExam 试卷
     * @return 试卷
     */
    @Override
    public List<JsExam> selectJsExamList(JsExam jsExam)
    {
        return jsExamMapper.selectJsExamList(jsExam);
    }

    /**
     * 新增试卷
     *
     * @param jsExam 试卷
     * @return 结果
     */
    @Override
    public int insertJsExam(JsExam jsExam)
    {
        jsExam.setCreateTime(DateUtils.getNowDate());
        return jsExamMapper.insertJsExam(jsExam);
    }

    /**
     * 修改试卷
     *
     * @param jsExam 试卷
     * @return 结果
     */
    @Override
    public int updateJsExam(JsExam jsExam)
    {
        jsExam.setUpdateTime(DateUtils.getNowDate());
        return jsExamMapper.updateJsExam(jsExam);
    }

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    @Override
    public int deleteJsExamByIds(Long[] ids)
    {
        return jsExamMapper.deleteJsExamByIds(ids);
    }

    /**
     * 删除试卷信息
     *
     * @param id 试卷ID
     * @return 结果
     */
    @Override
    public int deleteJsExamById(Long id)
    {
        return jsExamMapper.deleteJsExamById(id);
    }
}
