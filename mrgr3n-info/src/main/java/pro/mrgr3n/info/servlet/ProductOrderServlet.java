package pro.mrgr3n.info.servlet;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import pro.mrgr3n.info.InfoConstants;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

@SlingServlet(
        paths = "/mrgr3n/ebc/info",
        extensions = "html",
        methods = "GET")
public class ProductOrderServlet extends SlingSafeMethodsServlet {

    @Reference
    private EventAdmin eventAdmin;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String items = request.getParameter("items");
        String tempVae = "empty";
        if (items != null) {
            String orderId = generateId();
            tempVae = orderId;
            if (createNode(orderId)) {
                eventAdmin.postEvent(getEvent(orderId, items));
            }
        }

        System.out.println("\n|---------->Start:\nId: " + tempVae);
        response.getWriter().write("Id: " + tempVae);
    }

    private boolean createNode(String orderId) {
        return true;
    }

    private Event getEvent(String orderId, String items) {
        Dictionary<String, Object> result = new Hashtable<String, Object>();

        result.put("orderId", orderId);
        result.put("productNumbers", items);

        return new Event(InfoConstants.CREATE_ORDER_EVENT, result);
    }

    private String generateId() {
        return UUID.randomUUID().toString();

    }
}
