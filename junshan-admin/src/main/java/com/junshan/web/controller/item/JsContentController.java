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
import com.junshan.item.domain.JsContent;
import com.junshan.item.service.IJsContentService;
import com.junshan.common.utils.poi.ExcelUtil;
import com.junshan.common.core.page.TableDataInfo;

/**
 * 文本Controller
 *
 * @author czh
 * @date 2021-07-01
 */
@RestController
@RequestMapping("/item/content")
public class JsContentController extends BaseController
{
    @Autowired
    private IJsContentService jsContentService;

    /**
     * 查询文本列表
     */
    @PreAuthorize("@ss.hasPermi('item:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(JsContent jsContent)
    {
        startPage();
        List<JsContent> list = jsContentService.selectJsContentList(jsContent);
        return getDataTable(list);
    }

    /**
     * 导出文本列表
     */
    @PreAuthorize("@ss.hasPermi('item:content:export')")
    @Log(title = "文本", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JsContent jsContent)
    {
        List<JsContent> list = jsContentService.selectJsContentList(jsContent);
        ExcelUtil<JsContent> util = new ExcelUtil<JsContent>(JsContent.class);
        return util.exportExcel(list, "文本数据");
    }

    /**
     * 获取文本详细信息
     */
    @PreAuthorize("@ss.hasPermi('item:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(jsContentService.selectJsContentById(id));
    }

    /**
     * 新增文本
     */
    @PreAuthorize("@ss.hasPermi('item:content:add')")
    @Log(title = "文本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JsContent jsContent)
    {
        return toAjax(jsContentService.insertJsContent(jsContent));
    }

    /**
     * 修改文本
     */
    @PreAuthorize("@ss.hasPermi('item:content:edit')")
    @Log(title = "文本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JsContent jsContent)
    {
        return toAjax(jsContentService.updateJsContent(jsContent));
    }

    /**
     * 删除文本
     */
    @PreAuthorize("@ss.hasPermi('item:content:remove')")
    @Log(title = "文本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jsContentService.deleteJsContentByIds(ids));
    }
}
