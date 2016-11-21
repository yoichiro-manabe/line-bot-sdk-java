package com.example.bot.spring.echo.request;

import com.linecorp.bot.model.event.message.TextMessageContent;
import lombok.Data;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */

@Data
public abstract class RequestMessage {

    private RequestType requestType;

    public abstract String message();

    public static RequestMessage create(TextMessageContent textMessageContent){

        MessageParser parser = new MessageParser();
        parser.parse(textMessageContent);
        RequestType requestType = parser.getRequestType();

        if(requestType == RequestType.GET_ITEM_LIST){
            return new GetItemListRequest();
        }

        if(requestType == RequestType.GET_ITEM){
            return new GetItemRequest(parser.getMessageParseValue());
        }
        if(requestType == RequestType.GET_ITEM_STOCK){
            return new GetItemStockRequest(parser.getMessageParseValue());
        }

        if(requestType == RequestType.UNKNOWN){
            return new UnknowRequest();
        }

        throw new IllegalStateException();
    }
}
