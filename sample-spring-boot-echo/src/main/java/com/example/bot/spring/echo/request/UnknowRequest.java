package com.example.bot.spring.echo.request;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class UnknowRequest extends RequestMessage {
    @Override
    public String message(){
        return " メッセージには「商品一覧」「商品:商品名」「在庫数:商品コード」を含めてください。";
    }
}
