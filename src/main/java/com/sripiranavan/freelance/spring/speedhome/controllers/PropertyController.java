package com.sripiranavan.freelance.spring.speedhome.controllers;

import com.sripiranavan.freelance.spring.speedhome.dto.ApproveProperty;
import com.sripiranavan.freelance.spring.speedhome.entities.Property;
import com.sripiranavan.freelance.spring.speedhome.services.PropertiesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("properties")
public class PropertyController {
    private PropertiesService service;
    public PropertyController(PropertiesService service){
        this.service = service;
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping(name = "")
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size){
        try {
            List<Property> properties = new ArrayList<>();
            Pageable pageable = PageRequest.of(page,size);

            Page<Property> pageProperty = service.findAll(pageable);
            properties = pageProperty.getContent();
            Map<String,Object> response = new HashMap<>();
            response.put("properties",properties);
            response.put("currentPage",pageProperty.getNumber());
            response.put("totalItems",pageProperty.getTotalElements());
            response.put("totalPages",pageProperty.getTotalPages());

            return new ResponseEntity(response,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(name = "")
    public ResponseEntity saveProperty(@Valid @ModelAttribute Property property, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity("All the fields are required", HttpStatus.BAD_REQUEST);
        }else {
            if (service.findByPropertyName(property.getPropertyName()).isPresent()){
                return new ResponseEntity("This property name already exist",HttpStatus.CONFLICT);
            }else {
                service.saveProperty(property);
                return new ResponseEntity("Successfully created",HttpStatus.CREATED);
            }
        }
    }

    @PutMapping(name = "")
    public ResponseEntity updateProperty(@Valid @ModelAttribute Property property,BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity("There were some problem with your request", HttpStatus.BAD_REQUEST);
        }else {
            var pro=service.findByPropertyName(property.getPropertyName());
            if (pro.isPresent()){
                Property tempProperty = new Property();
                tempProperty.setId(pro.get().getId());
//              check the uniqueness
                if (service.findByPropertyName(property.getPropertyName()).isPresent()){
                    tempProperty.setPropertyName(pro.get().getPropertyName());
                }else {
                    tempProperty.setPropertyName(property.getPropertyName());
                }
                tempProperty.setPropertyType(property.getPropertyType());
                tempProperty.setPropertyDescription(property.getPropertyDescription());
                tempProperty.setPropertyStatus(pro.get().isPropertyStatus());
                service.saveProperty(tempProperty);
                return new ResponseEntity("Successfully updated ",HttpStatus.OK);
            }else {
                return new ResponseEntity("There are no record exists",HttpStatus.CONFLICT);
            }
        }
    }

    @PutMapping("status")
    public ResponseEntity updatePropertyStatus(@Valid @ModelAttribute ApproveProperty property,BindingResult result){
        if (result.hasErrors()){
            System.out.println(result);
            return new ResponseEntity("There were some problem with your request", HttpStatus.BAD_REQUEST);
        }else {
            var pro=service.findById(property.getId());
            if (pro.isPresent()){
                Property temPro = pro.get();
                temPro.setId(property.getId());
                temPro.setPropertyStatus(property.isStatus());
                service.saveProperty(temPro);
                return new ResponseEntity("Successfully updated Project status ",HttpStatus.OK);
            }else {
                return new ResponseEntity("There is no record exist regarding your request", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("filter")
    public ResponseEntity searchByField(@RequestParam(required = false,value = "search") String search,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size){
        try{
            List<Property> properties = new ArrayList<>();
            Pageable pageable = PageRequest.of(page,size);

            Page<Property> pageProperty;
            if (search.isEmpty()){
                pageProperty = service.findAll(pageable);
            }else {
                pageProperty= service.findAllBySearch(search,pageable);
            }
            properties = pageProperty.getContent();

            Map<String,Object> response = new HashMap<>();
            response.put("properties",properties);
            response.put("currentPage",pageProperty.getNumber());
            response.put("totalItems",pageProperty.getTotalElements());
            response.put("totalPages",pageProperty.getTotalPages());

            return new ResponseEntity(response,HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("status")
    public ResponseEntity searchByStatus(@RequestParam(required = false,value = "status") boolean status,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "3") int size){
        try {
            List<Property> properties = new ArrayList<>();
            Pageable pageable = PageRequest.of(page, size);

            Page<Property> pageProperty = service.findAllByPropertyStatus(status,pageable);
            properties = pageProperty.getContent();
            Map<String,Object> response = new HashMap<>();
            response.put("properties",properties);
            response.put("currentPage",pageProperty.getNumber());
            response.put("totalItems",pageProperty.getTotalElements());
            response.put("totalPages",pageProperty.getTotalPages());

            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
