package com.example.bot.spring.echo.request;

import com.linecorp.bot.model.event.message.TextMessageContent;
import lombok.Data;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */

@Data
public class MessageParser {

    private String messageParseValue;
    private RequestType requestType;

    public void parse(TextMessageContent textMessageContent){
        String s = textMessageContent.getText();

        System.out.println("request message:" + s);

        if(s.contains( RequestType.GET_ITEM_LIST.getDisplayString())){
            requestType= RequestType.GET_ITEM_LIST;
            messageParseValue = "";
            return;
        }

        if(s.contains(RequestType.GET_ITEM.getDisplayString())){
            requestType= RequestType.GET_ITEM;
            messageParseValue = s.split(":")[1];
            return;
        }

        if(s.contains(RequestType.GET_ITEM_STOCK.getDisplayString())){
            requestType= RequestType.GET_ITEM_STOCK;
            messageParseValue = s.split(":")[1];
            return;
        }

        requestType= RequestType.UNKNOWN;
    }
}
