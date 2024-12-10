package main.behaviors;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class SenderBehavior extends TickerBehaviour {
    private static final long serialVersionUID = 1L;
    private final String[] agentNames;

    public SenderBehavior(Agent agent, long period, String[] agentNames) {
        super(agent, period);
        this.agentNames = agentNames;
    }

    @Override
    protected void onTick() {
        // Choose a random agent to send a message to (not itself)
        String receiver;
        do {
            receiver = agentNames[new Random().nextInt(agentNames.length)];
        } while (receiver.equals(myAgent.getLocalName()));

        // Create and send a REQUEST message
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID(receiver, AID.ISLOCALNAME));
        request.setContent("Ce ora este?");
        myAgent.send(request);
        System.out.println(myAgent.getLocalName() + " sent a REQUEST to " + receiver);
    }
}