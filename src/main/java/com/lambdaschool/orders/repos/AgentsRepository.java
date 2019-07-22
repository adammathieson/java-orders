package com.lambdaschool.orders.repos;

import com.lambdaschool.orders.model.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agents, Long>
{
}
