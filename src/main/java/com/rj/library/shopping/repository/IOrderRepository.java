package com.rj.library.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rj.library.shopping.models.OrderEntity;

@Repository
public interface IOrderRepository extends MongoRepository<OrderEntity, String> {

}
