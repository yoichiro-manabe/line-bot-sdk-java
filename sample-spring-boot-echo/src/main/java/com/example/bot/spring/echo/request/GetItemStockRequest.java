package com.example.bot.spring.echo.request;

import com.example.bot.spring.echo.reuse.Item;
import com.example.bot.spring.echo.reuse.ItemService;

import java.util.Optional;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class GetItemStockRequest extends RequestMessage {
    private String itemValue;

    public GetItemStockRequest(String messageParseValue){
        itemValue = messageParseValue;
    }

    @Override
    public String message(){
        ItemService itemService = new ItemService();
        Optional<Item> item = itemService.findItem(itemValue);

        if(item.isPresent()){
            return item.get().itemStockDisplay();
        }else{
            return "該当する商品情報が存在しませんでした。検索した商品コードは" + itemValue + "です。";
        }
    }
}
