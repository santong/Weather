package me.santong.weather.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import me.santong.weather.models.Weather;

/**
 * Created by santong.
 * At 16/5/25 20:52
 */
public class WeatherAdapter extends TypeAdapter<Weather> {
    @Override
    public void write(JsonWriter out, Weather value) throws IOException {

    }

    @Override
    public Weather read(JsonReader in) throws IOException {
        return null;
    }
}
