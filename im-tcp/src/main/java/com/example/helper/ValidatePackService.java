package com.example.helper;

import com.example.pack.Basepack;

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
        return true;
    }
}
