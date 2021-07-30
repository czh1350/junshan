package com.junshan.web.controller.item;

import java.util.List;
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
import com.junshan.item.domain.JsSubject;
import com.junshan.item.service.IJsSubjectService;
import com.junshan.common.utils.poi.ExcelUtil;
import com.junshan.common.core.page.TableDataInfo;

/**
 * 课程Controller
 *
 * @author czh
 * @date 2021-07-01
 */
@RestController
@RequestMapping("/item/subject")
public class JsSubjectController extends BaseController
{
    @Autowired
    private IJsSubjectService jsSubjectService;

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('item:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(JsSubject jsSubject)
    {
        startPage();
        List<JsSubject> list = jsSubjectService.selectJsSubjectList(jsSubject);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('item:subject:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JsSubject jsSubject)
    {
        List<JsSubject> list = jsSubjectService.selectJsSubjectList(jsSubject);
        ExcelUtil<JsSubject> util = new ExcelUtil<JsSubject>(JsSubject.class);
        return util.exportExcel(list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('item:subject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(jsSubjectService.selectJsSubjectById(id));
    }

    /**
     * 新增课程
     */
    @PreAuthorize("@ss.hasPermi('item:subject:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JsSubject jsSubject)
    {
        return toAjax(jsSubjectService.insertJsSubject(jsSubject));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('item:subject:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JsSubject jsSubject)
    {
        return toAjax(jsSubjectService.updateJsSubject(jsSubject));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('item:subject:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jsSubjectService.deleteJsSubjectByIds(ids));
    }
}
