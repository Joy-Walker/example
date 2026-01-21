package com.example.helper;

import com.example.pack.Basepack;
import org.apache.commons.lang3.StringUtils;

/**
 * @author :panligang
 * @description :
 * @create :2025-07-17 15:31:00
 */

public class ValidatePackService {

    public static boolean validatePack(Basepack pack) {
        Long formId = pack.getFormId();
        Long toId = pack.getToId();
        if (formId == null || toId == null) {
           return false;
        }
        if(StringUtils.isEmpty(pack.getMessageKey())) {
            return false;
        }
        return true;
    }
}
