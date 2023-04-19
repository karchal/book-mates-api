package com.codecool.bookclub.event.repository;


import com.codecool.bookclub.event.model.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
}
