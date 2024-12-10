package main;

import jade.core.Agent;
import main.behaviors.SenderBehavior;
import main.behaviors.ReceiverBehavior;

public class Ag_chat extends Agent {
    private static final long serialVersionUID = 1L;
    private static final String[] AGENTS = {"Agent_1", "Agent_2", "Agent_3"};

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " is ready.");

        // Add Sender and Receiver Behaviors
        addBehaviour(new SenderBehavior(this, 2000, AGENTS)); // Send messages every 2 seconds
        addBehaviour(new ReceiverBehavior(this)); // Always listening for messages
    }
}