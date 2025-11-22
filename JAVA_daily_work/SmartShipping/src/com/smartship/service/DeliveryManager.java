package com.smartship.service;

import com.smartship.model.*;
import com.smartship.exception.*;
import java.util.*;

public class DeliveryManager {
    private List<Agent> agents = new ArrayList<>();
    private PriorityQueue<PackageItem> packageQueue = new PriorityQueue<>();

    private static final int MAX_LOAD = 3;

    public void addAgent(Agent agent) throws DuplicateEntryException {
        for (Agent a : agents)
            if (a.getName().equals(agent.getName()))
                throw new DuplicateEntryException("Agent already exists!");
        agents.add(agent);
    }

    public void addPackage(PackageItem pkg) {
        packageQueue.offer(pkg);
    }

    public void assignPackages() throws AgentNotAvailableException, OverloadException {
        while (!packageQueue.isEmpty()) {
            PackageItem pkg = packageQueue.poll();

            List<Agent> available = agents.stream()
                .filter(a -> a.getCity().equalsIgnoreCase(pkg.getDestinationCity()))
                .sorted(Comparator.comparingInt(Agent::getLoad))
                .toList();

            if (available.isEmpty())
                throw new AgentNotAvailableException("No agent available in " + pkg.getDestinationCity());

            Agent chosen = available.get(0);

            if (chosen.getLoad() >= MAX_LOAD)
                throw new OverloadException(chosen.getName() + " has reached max load!");

            chosen.assignPackage(pkg);
            System.out.println("Assigned " + pkg + " → " + chosen.getName());
        }
    }
}
