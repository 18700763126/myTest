package com.his.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author liyan
 * @Date 2021-02-21
 * @Version ： V1.0
 * @Description:
 */
@ApiModel(value="com-bjsxt-vo-DataGridView")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Long total;
    private List<?> data;

}
