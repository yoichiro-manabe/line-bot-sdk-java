/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import static java.util.Collections.singletonList;

import com.example.bot.spring.echo.request.MessageParser;
import com.example.bot.spring.echo.request.RequestMessage;
import com.example.bot.spring.echo.reuse.ItemCollection;
import com.example.bot.spring.echo.reuse.ItemService;
import com.example.bot.spring.echo.request.RequestType;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.profile.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import retrofit2.Response;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {

        System.out.println("event: " + event);

        Response<UserProfileResponse> response =
                LineMessagingServiceBuilder
                        .create(System.getenv("LINE_BOT_CHANNEL_TOKEN"))
                        .build()
                        .getProfile(event.getSource().getUserId())
                        .execute();

        String userName = "";

        if (response.isSuccessful()) {
            UserProfileResponse profile = response.body();
            System.out.println(profile.getDisplayName());
            System.out.println(profile.getPictureUrl());
            System.out.println(profile.getStatusMessage());

            userName = profile.getDisplayName();
        } else {
            System.out.println(response.code() + " " + response.message());
        }

        RequestMessage message = RequestMessage.create(event.getMessage());
        final BotApiResponse apiResponse = lineMessagingClient
                .replyMessage(new ReplyMessage(event.getReplyToken(),
                                               singletonList(new TextMessage(userName + " さん" + message))))
                .get();
        System.out.println("Sent messages: " + apiResponse);

//        final BotApiResponse apiResponse = lineMessagingClient
//                .replyMessage(new ReplyMessage(event.getReplyToken(),
//                                               singletonList(new TextMessage(userName + " さん " + event.getMessage().getText()))))
//                .get();
//        System.out.println("Sent messages: " + apiResponse);
//
//
//
    }

    @EventMapping
    public void defaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

}
