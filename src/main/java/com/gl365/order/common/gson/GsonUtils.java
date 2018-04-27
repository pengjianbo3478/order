package com.gl365.order.common.gson;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class GsonUtils {
	public static String toJson(Object src) {
		return GsonUtils.getGson().toJson(src);
	}

	public static Gson getGson() {
		Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonDeserializer());
		gson = gsonBuilder.create();
		return gson;
	}
}
