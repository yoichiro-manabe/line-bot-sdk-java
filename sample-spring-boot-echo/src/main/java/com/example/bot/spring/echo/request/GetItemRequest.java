package com.example.bot.spring.echo.request;

import com.example.bot.spring.echo.reuse.Item;
import com.example.bot.spring.echo.reuse.ItemCollection;
import com.example.bot.spring.echo.reuse.ItemService;

import java.util.Optional;


/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class GetItemRequest extends RequestMessage {
    private String itemName;

    public GetItemRequest(String messageParseValue){
        itemName = messageParseValue;
    }

    @Override
    public String message(){

        ItemService itemService = new ItemService();
        Optional<Item> item = itemService.findItem(itemName);

        if(item.isPresent()){
            return item.toString();
        }else{
            return "該当する商品情報が存在しませんでした。";
        }
    }
}
