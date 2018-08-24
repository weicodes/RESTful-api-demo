package com.wd.rest.controller;

import com.wd.rest.pojo.Account;
import com.wd.rest.pojo.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "账户相关接口",tags = "储蓄账户操作接口")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    static Map<String, Account> dataMap = Collections.synchronizedMap(new HashMap<String, Account>());

    @ApiOperation(value = "获取账户列表", notes = "获取账户详情列表")
    @ResponseBody
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getUserList() {
        ResponseEntity<List<Account>> response = new ResponseEntity<List<Account>>();
        response.setData(new ArrayList<Account>(dataMap.values()));
        return response;
    }

    @ApiOperation(value = "开账", notes = "新建金额账户,返回账户ID")
    @ApiImplicitParam(name = "name", value = "用户姓名", required = true, paramType = "form", dataType = "String")
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addAccount(@RequestParam(required = true, value = "name") String name) {
        ResponseEntity<String> response = new ResponseEntity<String>();
        String id = UUID.randomUUID().toString().replace("-","");
        Account account = new Account();
        account.setId(id);
        account.setMoney(0L);
        account.setName(name);
        dataMap.put(id, account);
        response.setData(id);
        return response;
    }

    @ApiOperation(value = "获取账户详细信息", notes = "根据账户ID查询详细信息")
    @ApiImplicitParam(name = "id", value = "账户ID", required = true, paramType="path", dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getUserById(@PathVariable String id) {
        ResponseEntity<Account> response = new ResponseEntity<Account>();
        response.setData(dataMap.get(id));
        return response;
    }

    @ApiOperation(value = "销户", notes = "注销账户")
    @ApiImplicitParam(name = "id", value = "账户ID", required = true, paramType="path", dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/{id}/close", method = RequestMethod.PUT)
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        ResponseEntity<String> response = new ResponseEntity<String>();
        Account account = dataMap.get(id);
        if(account==null){
            response.setMessage("账户不存在");
        }else {
            dataMap.remove(id);
        }
        return response;
    }

    @ApiOperation(value = "消费", notes = "减少账户ID金额,金额不足操作失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账户ID", required = true, paramType="path", dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, paramType = "form", dataType = "Long")
    })
    @ResponseBody
    @RequestMapping(value = "/{id}/debit", method = RequestMethod.PUT)
    public ResponseEntity<String> debitById(@PathVariable String id, @RequestParam(required = true, value = "money") Long money) {
        ResponseEntity<String> response = new ResponseEntity<String>();
        Account account = dataMap.get(id);
        if(account==null){
            response.setMessage("账户不存在");
        }else if(account.getMoney() < money){
            response.setMessage("余额不足");
        }else {
            account.setMoney(account.getMoney() - money);
        }
        return response;
    }

    @ApiOperation(value = "充值", notes = "增加账户ID金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账户ID", required = true, paramType="path", dataType = "String"),
            @ApiImplicitParam(name = "money", value = "金额", required = true, paramType = "form", dataType = "Long")
    })
    @ResponseBody
    @RequestMapping(value = "/{id}/credit", method = RequestMethod.PUT)
    public ResponseEntity<String> creditById(@PathVariable String id, @RequestParam(required = true, value = "money") Long money) {
        ResponseEntity<String> response = new ResponseEntity<String>();
        Account account = dataMap.get(id);
        if(account==null){
            response.setMessage("账户不存在");
        }else {
            account.setMoney(account.getMoney() + money);
        }
        return response;
    }
}
