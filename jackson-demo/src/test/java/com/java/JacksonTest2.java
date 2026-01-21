package com.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dserialization.*;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-21 15:16:00
 */
public class JacksonTest2 {

    @Test
    public void whenDeserializingUsingJsonCreator_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"theName\":\"My bean\"}";

        BeanWithCreator bean = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue(json);
        System.out.println(bean);
    }

    @Test
    public void whenDeserializingUsingJsonAnySetter_thenCorrect()
            throws IOException {
        String json
                = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";

        ExtendableBean bean = new ObjectMapper()
                .readerFor(ExtendableBean.class)
                .readValue(json);
        System.out.println(bean);
    }

    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect()
            throws IOException {

        String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";


        EventWithSerializer event = new ObjectMapper()
                .readerFor(EventWithSerializer.class)
                .readValue(json);

        System.out.println(event);
    }

    @Test
    public void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
        AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
    }

    @Test
    public void whenSerializingUsingJsonFormat_thenCorrect()
            throws IOException, ParseException {



        EventWithFormat event = new EventWithFormat("party", new Date());

        String result = new ObjectMapper().writeValueAsString(event);

        System.out.println(result);


        String data = new Date().toString();
        String s = "{\"name\":\"party\",\"eventDate\":\"21-01-2026 07:48:29\"}";

        EventWithFormat eventWithFormat = new ObjectMapper().readValue(s, EventWithFormat.class);
        System.out.println(eventWithFormat);

    }
}
