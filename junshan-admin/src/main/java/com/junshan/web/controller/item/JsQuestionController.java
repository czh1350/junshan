package com.junshan.web.controller.item;

import java.util.List;

import com.junshan.item.domain.vo.JsQuestionResponseVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.junshan.common.annotation.Log;
import com.junshan.common.core.controller.BaseController;
import com.junshan.common.core.domain.AjaxResult;
import com.junshan.common.enums.BusinessType;
import com.junshan.item.domain.JsQuestion;
import com.junshan.item.service.IJsQuestionService;
import com.junshan.common.utils.poi.ExcelUtil;
import com.junshan.common.core.page.TableDataInfo;

/**
 * 题目Controller
 *
 * @author czh
 * @date 2021-04-30
 */
@RestController
@RequestMapping("/item/question")
public class JsQuestionController extends BaseController
{
    @Autowired
    private IJsQuestionService jsQuestionService;

    /**
     * 查询题目列表
     */
    @PreAuthorize("@ss.hasPermi('item:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(JsQuestion jsQuestion)
    {
        startPage();
        List<JsQuestionResponseVo> list = jsQuestionService.selectJsQuestionList(jsQuestion);
        return getDataTable(list);
    }

    /**
     * 导出题目列表
     */
    @PreAuthorize("@ss.hasPermi('item:question:export')")
    @Log(title = "题目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JsQuestion jsQuestion)
    {
        List<JsQuestionResponseVo> list = jsQuestionService.selectJsQuestionList(jsQuestion);
        ExcelUtil<JsQuestionResponseVo> util = new ExcelUtil<>(JsQuestionResponseVo.class);
        return util.exportExcel(list, "题目数据");
    }

    /**
     * 获取题目详细信息
     */
    @PreAuthorize("@ss.hasPermi('item:question:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(jsQuestionService.selectJsQuestionById(id));
    }

    /**
     * 新增题目
     */
    @PreAuthorize("@ss.hasPermi('item:question:add')")
    @Log(title = "题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JsQuestion jsQuestion)
    {
        return toAjax(jsQuestionService.insertJsQuestion(jsQuestion));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('item:question:edit')")
    @Log(title = "题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JsQuestion jsQuestion)
    {
        return toAjax(jsQuestionService.updateJsQuestion(jsQuestion));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('item:question:remove')")
    @Log(title = "题目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jsQuestionService.deleteJsQuestionByIds(ids));
    }
}
