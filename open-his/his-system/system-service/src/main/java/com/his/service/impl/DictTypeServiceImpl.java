package com.his.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.constants.Constants;
import com.his.domain.dto.DictTypeDto;
import com.his.vo.DataGridView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.domain.DictType;
import com.his.mapper.DictTypeMapper;
import com.his.service.DictTypeService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @Author liyan
 * @Date 2021-02-21
 * @Version ： V1.0
 * @Description:
 */

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {


    @Override
    public DataGridView listPage(DictTypeDto dictTypeDto) {
        QueryWrapper<DictType> wrapper = new QueryWrapper<>();
        Page<DictType> page = new Page<>(dictTypeDto.getPageNum(), dictTypeDto.getPageSize());
        if (!StringUtils.isEmpty(dictTypeDto.getDictName())) {
            wrapper.like(DictType.COL_DICT_NAME, dictTypeDto.getDictName());
        }
        if (!StringUtils.isEmpty(dictTypeDto.getDictType())) {
            wrapper.like(DictType.COL_DICT_TYPE, dictTypeDto.getDictType());
        }
        if (!StringUtils.isEmpty(dictTypeDto.getStatus())) {
            wrapper.eq(DictType.COL_STATUS, dictTypeDto.getStatus());
        }
        if (!StringUtils.isEmpty(dictTypeDto.getBeginTime())) {
            wrapper.ge(DictType.COL_CREATE_TIME, dictTypeDto.getBeginTime());
        }
        if (!StringUtils.isEmpty(dictTypeDto.getEndTime())) {
            wrapper.ge(DictType.COL_CREATE_TIME, dictTypeDto.getEndTime());
        }
        Page<DictType> dict = baseMapper.selectPage(page, wrapper);
        return new DataGridView(dict.getTotal(), dict.getRecords());
    }

    @Override
    public DataGridView lists() {
        QueryWrapper<DictType> wrapper = new QueryWrapper<>();
        wrapper.eq(DictType.COL_STATUS, Constants.STATUS_TRUE);
        List<DictType> dictTypes = baseMapper.selectList(wrapper);
        return new DataGridView(null, dictTypes);
    }

    @Override
    public Boolean checkDictTypeUnique(Long dictId, String dictType) {
        dictId = dictId == null ? -1L : dictId;
        if (!StringUtils.isEmpty(dictType)) {
            QueryWrapper<DictType> wrapper = new QueryWrapper<>();
            wrapper.eq(DictType.COL_DICT_TYPE, dictType);
            DictType dict = baseMapper.selectOne(wrapper);
            if (ObjectUtils.isEmpty(dict)) {
                return false;
            }
            return true;
        }
        return null;
    }

    @Override
    public int insert(DictTypeDto dictTypeDto) {
        DictType type = new DictType();
        BeanUtil.copyProperties(dictTypeDto, type);
        type.setCreateTime(DateUtil.date());
        type.setCreateBy(dictTypeDto.getSimpleUser().getUserName());
        return baseMapper.insert(type);
    }

    @Override
    public int update(DictTypeDto dictTypeDto) {
        DictType type = new DictType();
        BeanUtil.copyProperties(dictTypeDto, type);
        type.setUpdateTime(DateUtil.date());
        type.setCreateBy(dictTypeDto.getSimpleUser().getUserName());
        return baseMapper.updateById(type);

    }

    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        if (!ArrayUtil.isEmpty(dictIds)) {
            List<Long> longs = Arrays.asList(dictIds);
            return baseMapper.deleteBatchIds(longs);
        }
        return -1;
    }

    @Override
    public DictType selectDictTypeById(Long dictId) {
        return baseMapper.selectById(dictId);
    }
}
