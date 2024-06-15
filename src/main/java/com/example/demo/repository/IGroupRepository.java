package com.example.demo.repository;

import com.example.demo.entities.Group;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.requests.EditGroupRequest;

import java.util.List;

public interface IGroupRepository {
    long addGroup(Group group) throws RepositoryException;

    void editGroup(EditGroupRequest editGroupRequest) throws RepositoryException;

    void deleteGroup(long id) throws RepositoryException;

    Group getGroupById(long id) throws RepositoryException;

    List<Group> getGroups() throws RepositoryException;
}
