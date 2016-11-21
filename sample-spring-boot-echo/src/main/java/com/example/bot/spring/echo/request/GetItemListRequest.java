package com.example.bot.spring.echo.request;

import com.example.bot.spring.echo.reuse.ItemCollection;
import com.example.bot.spring.echo.reuse.ItemService;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class GetItemListRequest extends RequestMessage {

    @Override
    public String message(){

        ItemService itemService = new ItemService();
        ItemCollection items = itemService.findAllItem();
        return items.message();
    }
}
