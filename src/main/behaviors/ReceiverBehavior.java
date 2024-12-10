package main.behaviors;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiverBehavior extends CyclicBehaviour {
    private static final long serialVersionUID = 1L;

    public ReceiverBehavior(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.REQUEST) {
                System.out.println(myAgent.getLocalName() + " received REQUEST from " + msg.getSender().getLocalName());
                // Respond with the current time
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
                reply.setContent("Ora este " + currentTime);
                myAgent.send(reply);
                System.out.println(myAgent.getLocalName() + " replied: " + reply.getContent());
            } else if (msg.getPerformative() == ACLMessage.INFORM) {
                System.out.println(myAgent.getLocalName() + " received INFORM: " + msg.getContent());
            }
        } else {
            block();
        }
    }
}
