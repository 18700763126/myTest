package com.his.controller.system;

import com.his.config.shiro.ShiroSecurityUtils;
import com.his.domain.dto.DictTypeDto;
import com.his.service.DictTypeService;
import com.his.vo.AjaxResult;
import com.his.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author liyan
 * @Date 2021-02-21
 * @Version ： V1.0
 * @Description:
 */
@RestController
@RequestMapping("system/dict/type")
public class DictTypeController {

  @Autowired
    private DictTypeService dictTypeService ;

    /**
     * 分页查询
     * @param dictTypeDto
     * @return
     */
    @GetMapping("listForPage")
    public AjaxResult listForPage(DictTypeDto dictTypeDto){
        DataGridView gridView = this.dictTypeService.listPage(dictTypeDto);
        return AjaxResult.success("查询成功",gridView.getData(),gridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addDictType")
    public AjaxResult addDictType(@Validated DictTypeDto dictTypeDto) {
        if (dictTypeService.checkDictTypeUnique(dictTypeDto.getDictId(), dictTypeDto.getDictType())) {
            return AjaxResult.fail("新增字典【" + dictTypeDto.getDictName() + "】失败，字典类型已存在");
        }
        dictTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictTypeService.insert(dictTypeDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateDictType")
    public AjaxResult updateDictType(@Validated DictTypeDto dictTypeDto) {
        if (dictTypeService.checkDictTypeUnique(dictTypeDto.getDictId(), dictTypeDto.getDictType())) {
            return AjaxResult.fail("修改字典【" + dictTypeDto.getDictName() + "】失败，字典类型已存在");
        }
        dictTypeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.dictTypeService.update(dictTypeDto));
    }


    /**
     * 根据ID查询一个字典信息
     */
    @GetMapping("getOne/{dictId}")
    public AjaxResult getDictType(@PathVariable @Validated @NotNull(message = "字典ID不能为空") Long dictId) {
        return AjaxResult.success(this.dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteDictTypeByIds/{dictIds}")
    public AjaxResult updateDictType(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] dictIds) {
        return AjaxResult.toAjax(this.dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 查询所有可用的字典类型
     */
    @GetMapping("selectAllDictType")
    public AjaxResult selectAllDictType(){
        return AjaxResult.success(this.dictTypeService.lists().getData());
    }

}
