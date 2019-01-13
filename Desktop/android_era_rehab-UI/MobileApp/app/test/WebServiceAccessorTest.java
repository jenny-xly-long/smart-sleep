package test;

//import org.junit.Test;

import ca.era.stride.persistence.WebServiceAccessor;


public class WebServiceAccessorTest {

//    @Test
    public void testServer(){
        int expectedResponse = 200;

        WebServiceAccessor wsaccess = new WebServiceAccessor();

        WebServiceAccessor.sendToWebService();
    }

}
