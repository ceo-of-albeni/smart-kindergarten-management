package kg.megalab.smart_kindergarten_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CRUDController <CreateDto, Dto>{

    @PostMapping
    ResponseEntity<Dto> save(@RequestBody CreateDto createDto);
    @PutMapping("/{id}")
    ResponseEntity<Dto> update(@RequestBody Dto dto);
    @DeleteMapping("/{id}")
    ResponseEntity<Dto> delete(@PathVariable Long id);
    @GetMapping("/{id}")
    ResponseEntity<Dto> findById(@PathVariable Long id);
    @GetMapping("?page={page}&size={size}")
    ResponseEntity<?> findAll(@RequestParam int pageNo, @RequestParam int pageSize);

}
