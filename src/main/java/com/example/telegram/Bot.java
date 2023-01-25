package com.example.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  @Override
  public String getBotUsername() {
    return "VeriDroidBot";
  }

  @Override
  public String getBotToken() {
    return "5823415115:AAHE3o0u8GcRzcWx1tk6LNai0OKd_13fPwE";
  }

  @Override
  public void onUpdateReceived(Update update) {
    Message msg = update.getMessage();
    User user = msg.getFrom();

    System.out.println(user.getFirstName() + " wrote " + msg.getText());
    System.out.println("The Thread name is " + Thread.currentThread().getName());
    System.out.println("The Thread name is " + Thread.currentThread().getId());


    Long userID = user.getId();

    sendText(userID, msg.getText());

  }

  public void sendText(Long who, String what) {
    SendMessage sm = SendMessage.builder()
        .chatId(who.toString()) // Who are we sending a message to
        .text(what).build(); // Message content
    try {
      execute(sm); // Actually sending the message
    } catch (TelegramApiException e) {
      throw new RuntimeException(e); // Any error will be printed here
    }
  }

}