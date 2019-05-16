package br.com.petrim.lich.serializer;

import br.com.petrim.lich.util.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class DateTimeStringSerializer extends JsonSerializer<Date> {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss"; //FIXME: recuperar de proriedades.

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (date != null) {
            jsonGenerator.writeString(DateUtil.getStringFromDate(date, DATE_TIME_FORMAT));
        } else {
            jsonGenerator.writeNull();
        }
    }
}
