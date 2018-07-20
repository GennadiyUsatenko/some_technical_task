package service;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapterService extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @Override
    public String marshal(Date v) throws Exception {
        return v != null ? dateFormat.format(v) : null;
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return v != null ? dateFormat.parse(v) : null;
    }

}