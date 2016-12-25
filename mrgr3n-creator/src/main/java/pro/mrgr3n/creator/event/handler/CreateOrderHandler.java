package pro.mrgr3n.creator.event.handler;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import pro.mrgr3n.creator.CreatorConstants;

import java.text.MessageFormat;
import java.util.Dictionary;
import java.util.Hashtable;


@Component
@Service(EventHandler.class)
@Property(name = EventConstants.EVENT_TOPIC, value = {CreatorConstants.CREATE_ORDER_EVENT})
public class CreateOrderHandler implements EventHandler {

    @Reference
    private EventAdmin eventAdmin;

    @Override
    public void handleEvent(Event event) {
        String orderId = event.getProperty("orderId").toString();
        String productNumbers = event.getProperty("productNumbers").toString();
        String property = event.getProperty(EventConstants.EVENT_TOPIC).toString();

        logToConsole(orderId, productNumbers, property);

        eventAdmin.postEvent(getEvent(orderId));
    }

    private void logToConsole(String orderId, String productNumbers, String property) {
        System.out.println("\n" + MessageFormat.format(
                "Order\nid: {0}.\nNumber products for creation {1}.\nEvent-topic: {2}",
                orderId,
                productNumbers,
                property));
    }

    private Event getEvent(String orderId) {
        Dictionary<String, Object> result = new Hashtable<String, Object>();

        result.put("orderId", orderId);

        return new Event(CreatorConstants.VALIDATE_ORDER_EVENT, result);
    }
}
