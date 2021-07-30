package com.junshan.item.mapper;

import java.util.List;
import com.junshan.item.domain.JsExam;

/**
 * 试卷Mapper接口
 *
 * @author czh
 * @date 2021-04-30
 */
public interface JsExamMapper
{
    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    public JsExam selectJsExamById(Long id);

    /**
     * 查询试卷列表
     *
     * @param jsExam 试卷
     * @return 试卷集合
     */
    public List<JsExam> selectJsExamList(JsExam jsExam);

    /**
     * 新增试卷
     *
     * @param jsExam 试卷
     * @return 结果
     */
    public int insertJsExam(JsExam jsExam);

    /**
     * 修改试卷
     *
     * @param jsExam 试卷
     * @return 结果
     */
    public int updateJsExam(JsExam jsExam);

    /**
     * 删除试卷
     *
     * @param id 试卷ID
     * @return 结果
     */
    public int deleteJsExamById(Long id);

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJsExamByIds(Long[] ids);
}
