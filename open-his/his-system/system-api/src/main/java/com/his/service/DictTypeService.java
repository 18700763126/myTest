package com.his.service;

import com.his.domain.DictType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.domain.dto.DictTypeDto;
import com.his.vo.DataGridView;

import java.util.List;

/**
 * @Author liyan
 * @Date 2021-02-21
 * @Version ： V1.0
 * @Description:
 */

public interface DictTypeService extends IService<DictType>{


    /**
     * 分页查询字典类型
     * @param dictTypeDto
     * @return
     */
    DataGridView listPage(DictTypeDto dictTypeDto);

    /**s
     * 查询所有字典类型
     * @return
     */
    DataGridView lists();

    /**
     * 检查字典类型是否存在
     * @param dictType
     * @return
     */
    Boolean checkDictTypeUnique(Long dictId,String dictType);

    /**
     * 插入新的字典类型
     * @param dictTypeDto
     * @return
     */
    int insert(DictTypeDto dictTypeDto);
    /**
     * 修改的字典类型
     * @param dictTypeDto
     * @return
     */
    int update(DictTypeDto dictTypeDto);

    /**
     * 根据ID删除字典类型
     * @param dictIds
     * @return
     */
    int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 根据ID查询一个字典类型
     * @param dictId
     * @return
     */
    DictType selectDictTypeById(Long dictId);

}
