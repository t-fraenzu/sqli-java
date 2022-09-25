package ch.mse.itsec.jakarta_jdbc.inbound;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchRequest {

    public String queryName;
    public String queryId;
}
