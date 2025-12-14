package io.github.jotamath.eventmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jotamath.eventmicroservice.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
