package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class ColorAdapter extends TypeAdapter<Color> {
    @Override
    public void write(JsonWriter jsonWriter, Color color) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("r");
        jsonWriter.value(color.getRed());
        jsonWriter.name("g");
        jsonWriter.value(color.getGreen());
        jsonWriter.name("b");
        jsonWriter.value(color.getBlue());
        jsonWriter.endObject();
    }

    @Override
    public Color read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String fieldname = null;

        int r = 0;
        int g = 0;
        int b = 0;
        while (jsonReader.hasNext()) {
            JsonToken token = jsonReader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = jsonReader.nextName();
            }


            if ("r".equals(fieldname)) {
                //move to next token
                token = jsonReader.peek();
                r = jsonReader.nextInt();
            }

            if ("g".equals(fieldname)) {
                //move to next token
                token = jsonReader.peek();
                g = jsonReader.nextInt();
            }

            if ("b".equals(fieldname)) {
                //move to next token
                token = jsonReader.peek();
                b = jsonReader.nextInt();
            }
        }
        jsonReader.endObject();
        return new Color(r, g, b);
    }
}
