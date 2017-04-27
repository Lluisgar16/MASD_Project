package masd.agent;

import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.bridge.ComponentIdentifier;
import jadex.bridge.fipa.SFipa;

	
/**
 * Send message to waiters that chef has no more orders to prepare.
 * @author Eirik
 *
 */
public class SendMessagePlan extends jadex.bdiv3x.runtime.Plan{


		@Override
		public void body() {
			
			/*
			IMessageEvent me = createMessageEvent("query_ref");
			me.getParameterSet(SFipa.RECEIVERS).addValue(cid);
			me.getParameter(SFipa.CONTENT).setValue("Chef has no more work to do"); 
			sendMessage(me);	
			*/
		}
}


