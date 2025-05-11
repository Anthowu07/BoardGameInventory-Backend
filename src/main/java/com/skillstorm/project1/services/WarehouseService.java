package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.InventoryRepo;
import com.skillstorm.project1.repositories.WarehouseRepo;

@Service
public class WarehouseService {
    
    @Autowired
    private WarehouseRepo repo;

    @Autowired
    private InventoryRepo inventoryRepo;

    // public WarehouseService(WarehouseRepo repo) {
    //     this.repo = repo;
    // }

    public Iterable<Warehouse> findAll(){
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id){
        return repo.findById(id);
    }

    //Used for PUT/POST requests
    public Warehouse save(Warehouse warehouse){
        return repo.save(warehouse);
    }

    //Used for DELETE requests
    
    @Transactional
    public void deleteById(int id) {
        List<Inventory> inventories = getInventoriesByWarehouseId(id);
        inventoryRepo.deleteAll(inventories);
        repo.deleteById(id);
    }

    //Return all inventories in a warehouse
    public List<Inventory> getInventoriesByWarehouseId(int warehouseId) {
        return inventoryRepo.findByWarehouseId(warehouseId);
    }
}
