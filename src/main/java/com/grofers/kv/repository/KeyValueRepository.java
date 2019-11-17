package com.grofers.kv.repository;

import com.grofers.kv.model.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface KeyValue repository.
 *
 * @author Prateek Ladha
 */
@Repository
public interface KeyValueRepository extends JpaRepository<KeyValue, Long> {
    KeyValue findOneByKey(String key);
}
