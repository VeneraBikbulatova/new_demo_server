package com.example.demo.services;

import com.example.demo.entities.Group;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IGroupRepository;
import com.example.demo.requests.AddGroupRequest;
import com.example.demo.requests.EditGroupRequest;

import java.util.List;

public class GroupService {
    private IGroupRepository groupRepository;

    public GroupService(IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public long addGroup(AddGroupRequest addGroupRequest) throws ServiceException {
        try{
            Group group = new Group(0, addGroupRequest.getName());
            groupRepository.addGroup(group);
            return group.getGroupId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addGroup name=" + addGroupRequest.getName(), r);
        }
    }

    public long editGroup(EditGroupRequest editGroupRequest) throws ServiceException{
        try{
            Group group = new Group(editGroupRequest.getId(), editGroupRequest.getName());
            groupRepository.editGroup(group);
            return group.getGroupId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editGroup id=" + editGroupRequest.getId(), r);
        }
    }

    public Group getGroupById(long id) throws ServiceException {
        try {
            return groupRepository.getGroupById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getGroupById id=" + id, r);
        }
    }

    public List<Group> getAllGroups() throws ServiceException{
        try{
            return groupRepository.getGroups();
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAllGroups");
        }
    }

    public void deleteGroup(long id) throws ServiceException{
        try{
            groupRepository.deleteGroup(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteGroup");
        }
    }

}
