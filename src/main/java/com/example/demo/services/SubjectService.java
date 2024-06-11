package com.example.demo.services;

import com.example.demo.entities.Subject;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.ISubjectRepository;
import com.example.demo.requests.AddSubjectRequest;
import com.example.demo.requests.EditSubjectRequest;

import java.util.List;

public class SubjectService {
    private ISubjectRepository subjectRepository;


    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public long addSubject(AddSubjectRequest addSubjectRequest) throws ServiceException {
        try{
            Subject subject = new Subject(0, addSubjectRequest.getName());
            subjectRepository.addSubject(subject);
            return subject.getId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addSubject name=" + addSubjectRequest.getName(), r);
        }
    }

    public long editSubject(EditSubjectRequest editSubjectRequest) throws ServiceException{
        try{
            Subject subject = new Subject(editSubjectRequest.getId(), editSubjectRequest.getName());
            subjectRepository.editSubject(subject);
            return subject.getId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editSubject id=" + editSubjectRequest.getId(), r);
        }
    }

    public Subject getSubjectById(long id) throws ServiceException {
        try {
            return subjectRepository.getSubjectById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getSubjectById id=" + id, r);
        }
    }

    public List<Subject> getAllSubjects() throws ServiceException{
        try{
            return subjectRepository.getSubjects();
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAllSubjects");
        }
    }

    public void deleteSubject(long id) throws ServiceException{
        try{
            subjectRepository.deleteSubject(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteSubject");
        }
    }
}
