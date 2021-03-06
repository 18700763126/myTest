package com.his.vo;

import com.his.constants.Constants;
import com.his.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author liyan
 * @Date 2021-02-20
 * @Version ： V1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser implements Serializable {

    private User user ;
    private List<String> roles = Collections.EMPTY_LIST;
    private List<String> permissions = Collections.EMPTY_LIST;


}
