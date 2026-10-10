package com.campusfind;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemType(String itemType);
    List<Item> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}