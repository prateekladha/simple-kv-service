/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.grofers.kv.controller;

import com.grofers.kv.model.KeyValue;
import com.grofers.kv.repository.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type KeyValue controller.
 *
 * @author Prateek Ladha
 */
@RestController
@RequestMapping("/api/v1")
public class KeyValueController {

    @Autowired
    private KeyValueRepository kvRepository;

    /**
     * Get all keys list.
     *
     * @return the list
     */
    @GetMapping("/keys")
    public List<KeyValue> getAllKeys() {
        return kvRepository.findAll();
    }

    /**
     * Gets value by key.
     *
     * @param key the key
     * @return the value by key
     */
    @GetMapping("/keys/{key}")
    public ResponseEntity<KeyValue> getValueByKey(@PathVariable(value = "key") String key) {
        KeyValue kv = kvRepository.findOneByKey(key);
        if(kv == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(kv);
    }

    /**
     * Create/Update key.
     *
     * @param kv the Key Value pair
     * @return the Key
     */
    @PostMapping("/keys")
    public KeyValue createUpdateKey(@Valid @RequestBody KeyValue kv) {
        KeyValue existingKV = kvRepository.findOneByKey(kv.getKey());
        if(existingKV != null) {
            existingKV.setValue(kv.getValue());
            existingKV.setCreatedAt(new Date());
            existingKV.setUpdatedAt(new Date());
            return kvRepository.save(existingKV);
        } else {
            kv.setCreatedAt(new Date());
            kv.setCreatedBy("System");
            kv.setUpdatedAt(new Date());
            kv.setUpdatedBy("System");
            return kvRepository.save(kv);
        }
    }

    /**
     * Delete key.
     *
     * @param key the key
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/keys/{key}")
    public Map<String, Boolean> deleteKey(@PathVariable(value = "key") String key) {
        Map<String, Boolean> response = new HashMap<>();
        KeyValue kv = kvRepository.findOneByKey(key);
        if(kv != null) {
            kvRepository.delete(kv);
            response.put("deleted", Boolean.TRUE);
        }
        return response;
    }
}