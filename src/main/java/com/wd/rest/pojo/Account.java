package com.wd.rest.pojo;


import io.swagger.annotations.ApiModelProperty;

public class Account {

    /** 用户卡号 */
    //@ApiModelProperty(hidden = true)
    @ApiModelProperty(name="id", value = "账户ID")
    private String id;
    /** 用户名 */
    @ApiModelProperty(name="name", value = "账户人姓名")
    private String name;
    /** 金额 */
    @ApiModelProperty(name="money", value = "账户余额")
    private Long money = 0L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
