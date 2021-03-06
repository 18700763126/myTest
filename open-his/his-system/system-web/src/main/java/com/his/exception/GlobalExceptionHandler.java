package com.his.exception;

import com.his.vo.AjaxResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 当系统出现MethodArgumentNotValidException这个异常时，会调用下面的方法
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e){
        List<Map<String,Object>> list = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError allError : allErrors) {
            Map<String,Object> map = new HashMap<>();
            map.put("defaultMessage",allError.getDefaultMessage());
            map.put("objectName",allError.getObjectName());
            FieldError error = (FieldError) allError;
            map.put("field",error.getField());
            list.add(map);
        }
        return AjaxResult.fail("后端数据校验失败",list);
    }


}
