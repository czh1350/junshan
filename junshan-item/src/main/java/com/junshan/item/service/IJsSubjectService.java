package com.junshan.item.service;

import java.util.List;
import com.junshan.item.domain.JsSubject;

/**
 * 课程Service接口
 *
 * @author czh
 * @date 2021-07-01
 */
public interface IJsSubjectService
{
    /**
     * 查询课程
     *
     * @param id 课程ID
     * @return 课程
     */
    public JsSubject selectJsSubjectById(Long id);

    /**
     * 查询课程列表
     *
     * @param jsSubject 课程
     * @return 课程集合
     */
    public List<JsSubject> selectJsSubjectList(JsSubject jsSubject);

    /**
     * 新增课程
     *
     * @param jsSubject 课程
     * @return 结果
     */
    public int insertJsSubject(JsSubject jsSubject);

    /**
     * 修改课程
     *
     * @param jsSubject 课程
     * @return 结果
     */
    public int updateJsSubject(JsSubject jsSubject);

    /**
     * 批量删除课程
     *
     * @param ids 需要删除的课程ID
     * @return 结果
     */
    public int deleteJsSubjectByIds(Long[] ids);

    /**
     * 删除课程信息
     *
     * @param id 课程ID
     * @return 结果
     */
    public int deleteJsSubjectById(Long id);
}
