package com.lambdaschool.orders.services;

import com.lambdaschool.orders.model.Agents;
import com.lambdaschool.orders.repos.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentsService")
public class AgentsServiceImp implements AgentsService
{
    @Autowired
    private AgentsRepository agentrepos;

    @Override
    public Agents findAgentById(long id) throws EntityNotFoundException
    {
        return agentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
