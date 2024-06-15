package com.example.demo;

import com.example.demo.entities.Group;
import com.example.demo.entities.Student;
import com.example.demo.exceptions.NotFoundService;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IGroupRepository;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.requests.EditStudentRequest;
import com.example.demo.responses.AddStudentResponse;
import com.example.demo.responses.GetGroupByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;
import com.example.demo.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private IStudentRepository studentRepository;
    @Mock
    private IGroupRepository groupRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    void addTest() throws NotFoundService, RepositoryException, ServiceException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Student student = Mockito.mock(Student.class);
//        when(student.getId()).thenReturn(studentId);
        when(studentRepository.addStudent(any(AddStudentRequest.class))).thenReturn(studentId);

        when(groupRepository.getGroupById(anyLong())).thenReturn(new Group());
        AddStudentRequest request = new AddStudentRequest("", "", "", 6L, null);
        //Act
        long res = studentService.addStudent(request);

        //Assert
        assertNotNull(res);
        assertEquals(studentId, res);
    }

    @Test
    void addTest_ThrowException() throws RepositoryException {
        //Arrange
        doAnswer(invocation -> {
            throw new NotFoundService("invalid studentGroup id");
        }).when(groupRepository).getGroupById(anyLong());
        AddStudentRequest request = new AddStudentRequest("", "", "", 0, null);

        //Assert
        assertThrows(NotFoundService.class, () -> studentService.addStudent(request));
    }

    @Test
    void deleteTest() throws ServiceException, RepositoryException, NotFoundService, InstantiationException, IllegalAccessException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        //Act
        studentService.deleteStudentById(studentId);

        //Assert
        verify(studentRepository, times(1)).deleteStudent(studentId);
    }

    @Test
    void deleteTest_ThrowException() throws RepositoryException {
        //Arrange
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundService("invalid student id");
        }).when(studentRepository).getStudentById(id);

        //Assert
        assertThrows(NotFoundService.class, () -> studentService.deleteStudentById(id));
    }

    @Test
    void editTest() throws ServiceException, RepositoryException, NotFoundService {
        //Arrange
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String lastName = "lastName";
        String firstName = "firstName";
        String middleName = "middleName";
        String status = "status";
        String group = Mockito.mock(Group.class).getName();
        long group_id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        Student student = new Student(firstName, middleName, lastName, group_id, id, status);
        String newLastName = "newLastName";
        String newFirstName = "newFirstName";
        String newMiddleName = "newMiddleName";
        String newStatus = "newStatus";
        String newGroupName = UUID.randomUUID().toString();
        long newGroupId = Mockito.mock(Group.class).getGroupId();
//        when(studentRepository.getStudentById(id)).thenReturn(student);
        when(groupRepository.getGroupById(newGroupId)).thenReturn(new Group());
        doAnswer(invocation -> {
            student.setLastname(newLastName);
            student.setFirstname(newFirstName);
            student.setPatronymic(newMiddleName);
            student.setStatus(newStatus);
            student.setGroup_id(newGroupId);
            return 0;
        }).when(studentRepository).editStudent(new Student(newFirstName, newMiddleName,  newLastName, newGroupId, id, newStatus));
        EditStudentRequest request = new EditStudentRequest(newFirstName, newMiddleName, newLastName, newGroupId, id, newStatus);

        //Act
        studentService.editStudent(request);

        //Assert
        verify(studentRepository, times(1)).editStudent(new Student(newFirstName, newMiddleName,  newLastName, newGroupId, id, newStatus));
        assertEquals(new Student(newFirstName, newMiddleName, newLastName, newGroupId, id, newStatus), student);
    }

    @Test
    void editTest_ThrowException_InvalidGroupId() throws RepositoryException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundService("invalid student id");
        }).when(studentRepository).getStudentById(studentId);

        EditStudentRequest request = new EditStudentRequest("f", "p", "l", 1, studentId, "s");

        //Assert
        assertThrows(NotFoundService.class, () -> studentRepository.editStudent(studentRepository.getStudentById(studentId)));
    }

    @Test
    void getTest() throws NotFoundService, RepositoryException, ServiceException {
        // Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long groupId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        Group group = new Group(groupId, "");

        Student student = new Student("", "", "", group.getGroupId(), studentId, "");
        when(studentRepository.getStudentById(studentId)).thenReturn(student);
        when(groupRepository.getGroupById(groupId)).thenReturn(group);

        // Act
        GetStudentByIdResponse newStudent = studentService.getStudentById(studentId);
        GetStudentByIdResponse response = new GetStudentByIdResponse(
                newStudent.getFirstname(),
                newStudent.getPatronymic(),
                newStudent.getLastname(),
                newStudent.getGroup_name(),
                newStudent.getId(),
                newStudent.getStatus()
        );

        // Assert
        assertNotNull(response);
        assertEquals(new GetStudentByIdResponse(
                student.getFirstname(),
                student.getPatronymic(),
                student.getLastname(),
                groupRepository.getGroupById(student.getGroup_id()).getName(),
                student.getId(),
                student.getStatus()
        ), response);
    }

    @Test
    void getTest_ThrowException() throws RepositoryException {
        //Arrange
        long studentId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        doAnswer(invocation -> {
            throw new NotFoundService("invalid student id");
        }).when(studentRepository).getStudentById(studentId);

        //Assert
        assertThrows(NotFoundService.class, () -> studentService.getStudentById(studentId));
    }


}


