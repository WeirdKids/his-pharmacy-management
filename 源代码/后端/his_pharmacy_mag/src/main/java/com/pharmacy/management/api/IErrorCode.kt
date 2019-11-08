package com.pharmacy.management.api
/**
 * @author 徐奥飞
 * @date 2019-11-5 8:40
 */
interface IErrorCode {
    fun getCode(): Long
    fun getMessage(): String
}