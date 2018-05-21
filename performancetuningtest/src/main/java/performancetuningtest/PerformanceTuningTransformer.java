package performancetuningtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.mule.api.MuleMessage;
import org.mule.api.registry.RegistrationException;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class PerformanceTuningTransformer extends AbstractMessageTransformer {
	  @SuppressWarnings("deprecation")
	  @Override
	  public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		  //System.out.println("***** Payload Class: " + message.getPayload().getClass().toString());
		  //Payload type: String
		  
		  //bid_info[0] is bid_amount, bid_info[1] is bidder_id
          String[] bid_info = message.getPayload().toString().split(";");
          String bid_amount = bid_info[0].substring(bid_info[0].indexOf("=")+1);
          String bidder_id = bid_info[1].substring(bid_info[1].indexOf("=")+1);
          ;
          if (Integer.parseInt(bid_amount) > Integer.parseInt(message.getMuleContext().getRegistry().get("max_bid_amount"))) {
        	  try {
        		message.getMuleContext().getRegistry().unregisterObject("max_bid_amount");
				message.getMuleContext().getRegistry().registerObject("max_bid_amount", bid_amount);
				message.getMuleContext().getRegistry().unregisterObject("max_bid_bidder_id");
				message.getMuleContext().getRegistry().registerObject("max_bid_bidder_id", bidder_id);
			  } catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
          } 
	      return message;
	  }

}
