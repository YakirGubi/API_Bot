package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.Time;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    private int sumOfRequests = 0;
    private ArrayList <Integer> SumPerDay = new ArrayList<>();
    private Map<Long,Id> Users = new HashMap<>();
    private String[] option = {"Random Joke" , "Number Fact" , "Cat Fact" , "Country Information" , "Quote"};
    private boolean[] boolOption;
    private String massage = "Welcome, We have 3 option: ";
    private int[] popularRequest = {0,0,0,0,0};
    private ArrayList<Id> usersID = new ArrayList<>();
    private int[] requestsLastDays = {0,0,0};

    public Bot(boolean[] boolOption){
        this.boolOption = boolOption;

        int count = 1;
        for(int i = 0 ; i < option.length ; i++){
            if(boolOption[i]){
                this.massage += "\n"+ count + ": ";
                this.massage += option[i];
                count++;
            }
        }
        this.massage += "\nEnter what you want";
    }

    @Override
    public String getBotUsername() {
        return "YG_InformationBot";
    }

    @Override
    public String getBotToken() {
        return "6118582570:AAExze6yEipGe1KOJhJXG-78Luevr37Sud4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Random random = new Random();

        new Thread(() -> {
            long chatId = update.getMessage().getChatId();
            Id id = Users.get(chatId);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (id == null) {
                this.sumOfRequests++;
                this.requestsLastDays[this.requestsLastDays.length-1] ++;
                id = new Id(update.getMessage().getChat().getUserName(), new Time(System.currentTimeMillis()), "Start");
                Users.put(chatId, id);
                this.usersID.add(id);
                sendMessage.setText(this.massage);
            } else {
                try {
                    this.sumOfRequests++;
                    this.requestsLastDays[this.requestsLastDays.length-1] ++;
                    id.setLastRequestT(new Time(System.currentTimeMillis()));
                    id.setSumOfRequests();

                    if (update.getMessage().getText().equals(this.option[0]) && boolOption[0]) {
                        id.setLastRequest("Joke");
                        this.popularRequest[0] ++;

                        HttpResponse<String> response = Unirest.get("https://official-joke-api.appspot.com/random_joke").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        RandomJokeModel randomJokeModel = objectMapper.readValue(response.getBody(), RandomJokeModel.class);

                        sendMessage.setText(randomJokeModel.getSetup() + "\n" + randomJokeModel.getPunchline() + "\n\nIf you want anther joke enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals("Anther One") && id.getLastRequest().equals("Joke") && boolOption[0]) {
                        id.setLastRequest("Joke");
                        this.popularRequest[0] ++;

                        HttpResponse<String> response = Unirest.get("https://official-joke-api.appspot.com/random_joke").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        RandomJokeModel randomJokeModel = objectMapper.readValue(response.getBody(), RandomJokeModel.class);

                        sendMessage.setText(randomJokeModel.getSetup() + "\n" + randomJokeModel.getPunchline() + "\n\nIf you want anther joke enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals(this.option[1]) && boolOption[1]) {
                        id.setLastRequest("NumberFact");
                        this.popularRequest[1] ++;

                        int num = random.nextInt();

                        HttpResponse<String> response = Unirest.get("http://numbersapi.com/" + num + "/math?callback=showNumber").asString();

                        sendMessage.setText(response.getBody() + "\n\nIf you want anther number fact enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals(this.option[2]) && boolOption[2]) {
                        id.setLastRequest("CatFact");
                        this.popularRequest[2] ++;

                        HttpResponse<String> response = Unirest.get("https://catfact.ninja/fact?max_length=140").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        CatFactsModel catFactsModel = objectMapper.readValue(response.getBody(), CatFactsModel.class);

                        sendMessage.setText(catFactsModel.getFact() + "\n\nIf you want anther cat fact enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals("Anther One") && id.getLastRequest().equals("CatFact") && boolOption[2]) {
                        id.setLastRequest("CatFact");
                        this.popularRequest[2] ++;

                        HttpResponse<String> response = Unirest.get("https://catfact.ninja/fact?max_length=140").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        CatFactsModel catFactsModel = objectMapper.readValue(response.getBody(), CatFactsModel.class);

                        sendMessage.setText(catFactsModel.getFact() + "\n\nIf you want anther cat fact enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals(this.option[3]) && boolOption[3]) {
                        id.setLastRequest("Country");

                        sendMessage.setText("Enter the alpha code of the country you want to know about:");

                    } else if (id.getLastRequest().equals("Country") && boolOption[3]) {
                        id.setLastRequest("Country");
                        this.popularRequest[3] ++;

                        HttpResponse<String> response = Unirest.get("https://restcountries.com/v2/alpha/" + update.getMessage().getText()).asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        CountryModel countryModel = objectMapper.readValue(response.getBody(), CountryModel.class);

                        String borders = "";
                        for (int i = 0; i < countryModel.getBorders().size(); i++) {
                            borders += countryModel.getBorders().get(i) + ", ";
                        }

                        sendMessage.setText("Name: " + countryModel.getName() + "\nCapital: " + countryModel.getCapital() + "\nBorders: " + borders + "\nPopulation: " + countryModel.getPopulation() + "\n\nIf you want anther country enter the alpha code of the country you want to know about:");

                    } else if (update.getMessage().getText().equals(this.option[4]) && boolOption[4]) {
                        id.setLastRequest("Quote");
                        this.popularRequest[4] ++;

                        HttpResponse<String> response = Unirest.get("https://api.quotable.io/random").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        QuoteModel quoteModel = objectMapper.readValue(response.getBody(), QuoteModel.class);

                        sendMessage.setText(quoteModel.getContent() + "\n" + quoteModel.getAuthor() + "\n\nIf you want anther quote enter: \"Anther One\"");

                    } else if (update.getMessage().getText().equals("Anther One") && id.getLastRequest().equals("Quote") && boolOption[4]) {
                        id.setLastRequest("Quote");
                        this.popularRequest[4] ++;

                        HttpResponse<String> response = Unirest.get("https://api.quotable.io/random").asString();

                        ObjectMapper objectMapper = new ObjectMapper();
                        QuoteModel quoteModel = objectMapper.readValue(response.getBody(), QuoteModel.class);

                        sendMessage.setText(quoteModel.getContent() + "\n" + quoteModel.getAuthor() + "\n\nIf you want anther quote enter: \"Anther One\"");
                    } else {
                        sendMessage.setText("Something want wrong plisse try again");
                        this.sumOfRequests--;
                    }
                } catch (Exception e) {
                    sendMessage.setText("Something want wrong plisse try again");
                    this.sumOfRequests--;
                    this.requestsLastDays[this.requestsLastDays.length-1] --;
                    System.out.println(e);
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public String mostActiveUser(){
        String name = "";
        int num = 0;
        for(int i = 0 ; i < this.Users.size() ; i++){
            if(this.usersID.get(i).getSumOfRequests() >= num){
                name = this.usersID.get(i).getName();
            }
        }
        return name;
    }
    public String mostPopularRequest(){
        String name = "";
        int num = 0;
        for(int i = 0 ; i < this.popularRequest.length ; i++){
            if(this.popularRequest[i] >= num){
                name = this.option[i];
                num = this.popularRequest[i];
            }
        }
        return name;
    }

    public Id[] getLastRequests() {
        Id[] lastRequests = new Id[10];
        int count = 0;

        for(int i = 9 ; i >= 0 ; i--){
            if(this.usersID.size() > i){
                lastRequests[count] = this.usersID.get(i);
                count++;
            }
        }

        return lastRequests;
    }

    public int getSumOfRequests() {
        return sumOfRequests;
    }
    public int getSumOfUsers(){
        return this.usersID.size();
    }

    public int[] getRequestsLastDays() {
        return requestsLastDays;
    }

    public void setRequestsLastDays() {
        this.requestsLastDays[this.requestsLastDays.length - 1] ++;
    }
    public void dayPass(){
        for(int i = this.requestsLastDays.length - 2 ; i >= 0 ; i--){
            this.requestsLastDays[i-1] = this.requestsLastDays[i];
        }
        this.requestsLastDays[this.requestsLastDays.length-1] = 0;
    }
}