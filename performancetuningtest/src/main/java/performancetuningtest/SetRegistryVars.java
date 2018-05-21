package performancetuningtest;

import org.mule.api.MuleMessage;
import org.mule.api.registry.RegistrationException;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class SetRegistryVars extends AbstractMessageTransformer {
	  @SuppressWarnings("deprecation")
	  @Override
	  public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
      	  try {
				message.getMuleContext().getRegistry().registerObject("max_bid_amount", "-1");
				message.getMuleContext().getRegistry().registerObject("max_bid_bidder_id", "-1");
		  } catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	      return message;
	  }

}
