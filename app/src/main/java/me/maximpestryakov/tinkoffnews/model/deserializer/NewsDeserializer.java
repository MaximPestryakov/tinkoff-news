package me.maximpestryakov.tinkoffnews.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import me.maximpestryakov.tinkoffnews.model.News;

import static me.maximpestryakov.tinkoffnews.model.News.CONTENT;
import static me.maximpestryakov.tinkoffnews.model.News.ID;
import static me.maximpestryakov.tinkoffnews.model.News.MILLISECONDS;
import static me.maximpestryakov.tinkoffnews.model.News.PUBLICATION_DATE;
import static me.maximpestryakov.tinkoffnews.model.News.TEXT;
import static me.maximpestryakov.tinkoffnews.model.News.TITLE;

public class NewsDeserializer implements JsonDeserializer<News> {

    @Override
    public News deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        News news = new News();

        if (jsonObject.has(CONTENT)) {
            news.setContent(jsonObject.get(CONTENT).getAsString());
        }

        if (jsonObject.has(TITLE)) {
            jsonObject = jsonObject.get(TITLE).getAsJsonObject();
        }

        news.setId(jsonObject.get(ID).getAsString());
        news.setTitle(jsonObject.get(TEXT).getAsString());
        news.setDate(jsonObject.get(PUBLICATION_DATE).getAsJsonObject().get(MILLISECONDS).getAsLong());

        return news;
    }
}
