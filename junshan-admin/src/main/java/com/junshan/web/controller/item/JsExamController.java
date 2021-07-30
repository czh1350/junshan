package com.junshan.web.controller.item;

import java.util.List;

import com.junshan.common.utils.poi.ExcelUtil;
import com.junshan.item.domain.JsExam;
import com.junshan.item.service.IJsExamService;
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
import com.junshan.common.core.page.TableDataInfo;

/**
 * 试卷Controller
 *
 * @author czh
 * @date 2021-04-30
 */
@RestController
@RequestMapping("/item/exam")
public class JsExamController extends BaseController
{
    @Autowired
    private IJsExamService jsExamService;

    /**
     * 查询试卷列表
     */
    @PreAuthorize("@ss.hasPermi('item:exam:list')")
    @GetMapping("/list")
    public TableDataInfo list(JsExam jsExam)
    {
        startPage();
        List<JsExam> list = jsExamService.selectJsExamList(jsExam);
        return getDataTable(list);
    }

    /**
     * 导出试卷列表
     */
    @PreAuthorize("@ss.hasPermi('item:exam:export')")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JsExam jsExam)
    {
        List<JsExam> list = jsExamService.selectJsExamList(jsExam);
        ExcelUtil<JsExam> util = new ExcelUtil<JsExam>(JsExam.class);
        return util.exportExcel(list, "试卷数据");
    }

    /**
     * 获取试卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('item:exam:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(jsExamService.selectJsExamById(id));
    }

    /**
     * 新增试卷
     */
    @PreAuthorize("@ss.hasPermi('item:exam:add')")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JsExam jsExam)
    {
        return toAjax(jsExamService.insertJsExam(jsExam));
    }

    /**
     * 修改试卷
     */
    @PreAuthorize("@ss.hasPermi('item:exam:edit')")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JsExam jsExam)
    {
        return toAjax(jsExamService.updateJsExam(jsExam));
    }

    /**
     * 删除试卷
     */
    @PreAuthorize("@ss.hasPermi('item:exam:remove')")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jsExamService.deleteJsExamByIds(ids));
    }
}
