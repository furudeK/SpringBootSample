package com.example.rest;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResult {
    /**リーターンコード*/
    private int result;

    /** エラーマップ
     * key:フィールド名
     * value:エラーメッセージ
     */
    private Map<String ,String> errors;
}
