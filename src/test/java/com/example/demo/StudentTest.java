//package com.example.demo;
//
//import com.example.demo.controllers.StudentController;
//import com.example.demo.entities.Student;
//import com.example.demo.exceptions.RepositoryException;
//import com.example.demo.exceptions.ServiceException;
//import com.example.demo.repository.GroupRepository;
//import com.example.demo.repository.IGroupRepository;
//import com.example.demo.repository.IStudentRepository;
//import com.example.demo.repository.StudentRepository;
//import com.example.demo.requests.AddStudentRequest;
//import com.example.demo.requests.EditStudentRequest;
//import com.example.demo.responses.AddStudentResponse;
//import com.example.demo.services.StudentService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import java.util.UUID;
//
//import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
//import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.RequestEntity.post;
//import static org.springframework.http.RequestEntity.put;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(StudentController.class)
//@ExtendWith(MockitoExtension.class)
//public class StudentTest {
//    @Mock
//    private StudentRepository studentRepository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudentService studentService;
//
//    private Student student;
//
//    private AddStudentRequest addStudentRequest;
//    private EditStudentRequest editStudentRequest;
//
//    @BeforeEach
//    public void setUp() {
//        student = new Student("Jonny", "D", "Sad", 1, 1, "status");
//        addStudentRequest = new AddStudentRequest("Jonny", "D", "Sad", 1, "status");
//        editStudentRequest = new EditStudentRequest("Jonny", "D", "Sad", 1, 1, "status");
//        studentRepository = new StudentRepository(new JdbcTemplate());
//    }
//
//    @Test
//    public void testAddStudent() throws Exception {
//        studentService = mock(StudentService.class);
//        when(studentService.addStudent(addStudentRequest)).thenReturn(1L);
//        long result = studentService.addStudent(addStudentRequest);
//        assertTrue(studentService.getStudentById(result).getId() == 0);
////        Mockito.when(studentService.addStudent(addStudentRequest)).thenAnswer(invocation -> {
////            Student studentToSave = invocation.getArgument(0);
////            studentToSave.setId(1L);
////            return studentToSave;
////        });
////
////        mockMvc.perform(post("/students")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(new ObjectMapper().writeValueAsString(addStudentRequest)))
////                .andExpect(status().isCreated())
////                .andExpect(jsonPath("$.id").value(1L))
////                .andExpect(jsonPath("$.firstName").value("John"))
////                .andExpect(jsonPath("$.lastName").value("Doe"))
////                .andExpect(jsonPath("$.middleName").value("Edwards"))
////                .andExpect(jsonPath("$.status").value("learning"));
//    }
//
//    @Test
//    public void testGetAllStudents() throws Exception {
//        studentService = mock(StudentService.class);
//        when(studentService.getStudents()).thenReturn(Arrays.asList(student));
//        List<Student> result = studentService.getStudents();
////        List<Student> students = Arrays.asList(student);
////        Mockito.when(studentService.getStudents()).thenReturn(students);
////
////        mockMvc.perform(get("/students"))
////                .andExpect(status().isOk())
////                .andExpect((ResultMatcher) jsonPath("$[0].firstName").value("Jonny"))
////                .andExpect((ResultMatcher) jsonPath("$[0].lastName").value("D"))
////                .andExpect((ResultMatcher) jsonPath("$[0].middleName").value("Sad"))
////                .andExpect((ResultMatcher) jsonPath("$[0].group_id").value(1L))
////                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
////                .andExpect((ResultMatcher) jsonPath("$[0].status").value("status"));
//    }
////
////    @Test
////    public void testUpdateStudent() throws Exception {
////        Mockito.when(studentService.getStudentById(anyLong())).thenReturn(Optional.of(student));
////        Mockito.when(studentService.editStudent(editStudentRequest)).thenReturn(student);
////
////        mockMvc.perform(put("/students/1")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(new ObjectMapper().writeValueAsString(editStudentRequest)))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.id").value(1L))
////                .andExpect(jsonPath("$.firstName").value("John"));
////    }
////
////    @Test
////    public void testGetStudentById() throws Exception {
////        Mockito.when(studentService.getStudentById(anyLong())).thenReturn(Optional.of(student));
////
////        mockMvc.perform(get("/students/1"))
////                .andExpect(status().isOk())
////                .andExpect((ResultMatcher) jsonPath("$.id").value(1L))
////                .andExpect((ResultMatcher) jsonPath("$.firstName").value("John"));
////    }
////
////    @Test
////    public void testDeleteStudent() throws Exception {
////        Mockito.doNothing().when(studentService).deleteStudentById(anyLong());
////
////        mockMvc.perform(delete("/students/1"))
////                .andExpect(status().isNoContent());
////    }
//
//    @Test
//    public void testGetStudent() throws Exception {
//        long id = anyLong();
//        studentService = mock(StudentService.class);
//        when(studentService.getStudentById(id)).thenReturn(student);
//        Student result = studentService.getStudentById(id);
//    }
//
//
//}
