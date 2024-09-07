package com.learning.flights.converters;

import com.learning.flights.domain.Aircraft;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AircraftWriteConverter implements Converter<Aircraft, Document> {
    @Override
    public Document convert(Aircraft source) {
        var document = new Document();
        document.put("model", source.getModel());
        document.put("capacity", source.getSeatCapacity());
        document.put("wakeTurbulance", source.getWakeTurbulence());

        var modelName = source.getModel().split(" ");
        if(modelName.length>1) {
            document.put("manufacturer", modelName[0]);
        }
        else document.put("manufacturer", "N/A");
        return document;
    }
}
