package com.kiekeboo.app.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Because the Date is serialized properly in the HTTP response by Jackson, this class is created.
 * With Jackson the date looks like this: 1379402301
 * Using this class the date looks normal: 11-06-15
 */
public class CustomDateSerializerService extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        gen.writeString(formattedDate);
    }


}
