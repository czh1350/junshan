package com.junshan.item.service.impl;

import java.util.List;
import com.junshan.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.junshan.item.mapper.JsSubjectMapper;
import com.junshan.item.domain.JsSubject;
import com.junshan.item.service.IJsSubjectService;

/**
 * 课程Service业务层处理
 *
 * @author czh
 * @date 2021-07-01
 */
@Service
@AllArgsConstructor
public class JsSubjectServiceImpl implements IJsSubjectService
{
    private JsSubjectMapper jsSubjectMapper;

    /**
     * 查询课程
     *
     * @param id 课程ID
     * @return 课程
     */
    @Override
    public JsSubject selectJsSubjectById(Long id)
    {
        return jsSubjectMapper.selectJsSubjectById(id);
    }

    /**
     * 查询课程列表
     *
     * @param jsSubject 课程
     * @return 课程
     */
    @Override
    public List<JsSubject> selectJsSubjectList(JsSubject jsSubject)
    {
        return jsSubjectMapper.selectJsSubjectList(jsSubject);
    }

    /**
     * 新增课程
     *
     * @param jsSubject 课程
     * @return 结果
     */
    @Override
    public int insertJsSubject(JsSubject jsSubject)
    {
        jsSubject.setCreateTime(DateUtils.getNowDate());
        return jsSubjectMapper.insertJsSubject(jsSubject);
    }

    /**
     * 修改课程
     *
     * @param jsSubject 课程
     * @return 结果
     */
    @Override
    public int updateJsSubject(JsSubject jsSubject)
    {
        jsSubject.setUpdateTime(DateUtils.getNowDate());
        return jsSubjectMapper.updateJsSubject(jsSubject);
    }

    /**
     * 批量删除课程
     *
     * @param ids 需要删除的课程ID
     * @return 结果
     */
    @Override
    public int deleteJsSubjectByIds(Long[] ids)
    {
        return jsSubjectMapper.deleteJsSubjectByIds(ids);
    }

    /**
     * 删除课程信息
     *
     * @param id 课程ID
     * @return 结果
     */
    @Override
    public int deleteJsSubjectById(Long id)
    {
        return jsSubjectMapper.deleteJsSubjectById(id);
    }
}
