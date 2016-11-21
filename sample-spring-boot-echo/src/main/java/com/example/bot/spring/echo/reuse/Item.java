package com.example.bot.spring.echo.reuse;

import lombok.Data;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */

@Data
public class Item {

    private String itemCode;
    private String itemName;
    private Integer itemPrice;
    private Integer itemStock;

    public Item(String itemCode, String itemName, Integer itemPrice, Integer itemStock){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
    }

    public String itemDisplay(){
        String format = "%s [%s] %d円 です。";
        return String.format(format,itemName, itemCode, itemPrice);
    }

    public String itemStockDisplay(){
        return "在庫数量は " + itemStock + " 個です。";
    }
}
