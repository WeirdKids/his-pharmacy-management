package com.pharmacy.management.controller

import com.pharmacy.management.api.CommonResult
import com.pharmacy.management.entity.User
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
/**
 * @author 徐奥飞
 * @date 2019-11-5 9:30
 */
@RestController //此注解是 @ResponseBody 和 @Controller 的组合注解，可返回一个 JSON
class LoginController {
    @RequestMapping(value = ["/admin/login"], method = [RequestMethod.POST])
    fun admin(@RequestBody user: User): CommonResult<*> {
        return if (user.username == "admin" && user.password == "123456") {
            CommonResult.success("admin")
        } else {
            CommonResult.validateFailed()
        }
    }
}