package com.solvd.db.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class JAXBDateAdapter extends XmlAdapter<String, Date> {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String dateStr) {
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String marshal(Date date) {
        return simpleDateFormat.format(date);
    }
}
