package com.example.demo.repository;

import com.example.demo.entities.Group;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface IGroupRepository {
    long addGroup(Group group) throws RepositoryException;

    void editGroup(Group group) throws RepositoryException;

    void deleteGroup(long id) throws RepositoryException;

    Group getGroupById(long id) throws RepositoryException;

    List<Group> getGroups() throws RepositoryException;
}
