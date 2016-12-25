package pro.mrgr3n.validator.handler;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import pro.mrgr3n.validator.ValidatorConstants;

import java.text.MessageFormat;


@Component
@Service(EventHandler.class)
@Property(name = EventConstants.EVENT_TOPIC, value = {ValidatorConstants.VALIDATE_ORDER_EVENT})
public class ValidateOrderHandler implements EventHandler {

    @Override
    public void handleEvent(Event event) {
        String orderId = event.getProperty("orderId").toString();
        String property = event.getProperty(EventConstants.EVENT_TOPIC).toString();

        logToConsole(orderId, property);
    }

    private void logToConsole(String orderId, String property) {
        System.out.println("\n" + MessageFormat.format(
                "Validated.\nOrder id: {0}.\nEvent-topic: {1}",
                orderId,
                property));
    }
}
