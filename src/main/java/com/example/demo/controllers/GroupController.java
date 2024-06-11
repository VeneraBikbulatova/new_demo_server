package com.example.demo.controllers;

import com.example.demo.entities.Group;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddGroupRequest;
import com.example.demo.requests.EditGroupRequest;
import com.example.demo.responses.AddGroupResponse;
import com.example.demo.responses.GetGroupByIdResponse;
import com.example.demo.services.GroupService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGroupByIdResponse> getById(
            @PathVariable ("id") long id
    ){
        try {
            Group forResponse = groupService.getGroupById(id);
            return new ResponseEntity<>(new GetGroupByIdResponse(
                    forResponse.getName(),
                    forResponse.getGroupId()
            ), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Group>> getAllGroups(){
        try{
            return new ResponseEntity<List<Group>>(groupService.getAllGroups(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity<?> addGroup(
            @RequestBody AddGroupRequest addGroupRequest
    ){
        try{
            return new ResponseEntity<>(groupService.addGroup(addGroupRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editGroup(
            @RequestBody EditGroupRequest editGroupRequest
    ){
        try{
            return new ResponseEntity<>(groupService.editGroup(editGroupRequest), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(
            @PathVariable ("id") long id
    ){
        try{
            groupService.deleteGroup(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
