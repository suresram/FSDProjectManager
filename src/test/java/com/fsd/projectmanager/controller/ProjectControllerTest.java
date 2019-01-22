package com.fsd.projectmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.ParentTaskVO;
import com.fsd.projectmanager.bo.ProjectVO;
import com.fsd.projectmanager.bo.TaskVO;
import com.fsd.projectmanager.bo.UserVO;
import com.fsd.projectmanager.service.impl.ProjectServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTest {
	
	@InjectMocks
	ProjectController target;
	
	@Mock
	private ProjectServiceImpl projectService;
	
	private MockMvc mockMvc;

	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testHome() {
		String testhome = target.testHome();
		Assert.assertEquals(testhome, "Project Manager App - Test Task ");
	}
	
	@Test
	public void getTasks() {
		
		List<TaskVO> tasks =new ArrayList<TaskVO>();
				
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("ABC");
		taskVO.setEndDate("01/20/2019");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("Parent");
		taskVO.setPriority("1");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("01/20/2019");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("Task");
		tasks.add(taskVO);
		
		when(projectService.getAllTasks()).thenReturn(tasks);
		List<TaskVO> tasksList = target.getTasks();
	}
	
	@Test
	public void getTasksById() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("ABC");
		taskVO.setEndDate("01/20/2019");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("Parent");
		taskVO.setPriority("1");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("01/20/2019");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("Task");
		
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		TaskVO task = target.getTasksById("1L");
		assertEquals("ABC",task.getEmployeeId());
		assertEquals("01/20/2019",task.getEndDate());
		task.getParentTaskId();
		assertEquals("Parent",task.getParentTaskName());
		assertEquals("1",task.getPriority());
		assertEquals("A",task.getStatus());
		task.getTaskId();
		assertEquals("Task",task.getTaskName());
		
	}
	
	@Test
	public void saveTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("ABC");
		taskVO.setEndDate("01/20/2019");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("Parent");
		taskVO.setPriority("1");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("01/20/2019");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("Task");
		boolean status = target.saveTask(taskVO);
		
	}
	
	@Test
	public void updateTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("ABC");
		taskVO.setEndDate("01/20/2019");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("Parent");
		taskVO.setPriority("1");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("01/20/2019");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("Task");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		
		boolean status = target.updateTask(taskVO,"1L");
		
	}
	
	@Test
	public void deleteTask() {
		TaskVO taskVO=new TaskVO();
		taskVO.setEmployeeId("ABC");
		taskVO.setEndDate("01/20/2019");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("Parent");
		taskVO.setPriority("1");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("01/20/2019");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("Task");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		boolean status = target.deleteTask("1L");
	}
	
	@Test
	public void getParentTasks() {
		
		List<ParentTaskVO> parentList=new ArrayList<ParentTaskVO>();
		ParentTaskVO parent=new ParentTaskVO();
		parent.setParentTaskId(1L);
		parent.setParentTaskName("parent1");
		parent.setProjectId(1L);
		
		
		when(projectService.getAllParentTasks()).thenReturn(parentList);
		List<ParentTaskVO> tasks =target.getParentTasks();
		
	}
	
	
	@Test
	public void getProjects() {
		List<ProjectVO> projectList =new ArrayList<ProjectVO>();
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("ABC");
		project.setEndDate("01/20/2019");
		project.setNoOfTask(1L);
		project.setPriority("1");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("01/20/2019");
		project.setStatus("Active");
		projectList.add(project);
		when(projectService.getAllProjects()).thenReturn(projectList);
		List<ProjectVO> tasks =target.getProjects();
		
	}
	
	@Test
	public void getProjectById() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("ABC");
		project.setEndDate("01/20/2019");
		project.setNoOfTask(1L);
		project.setPriority("2");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("01/20/2019");
		project.setStatus("Active");
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		ProjectVO tasks =target.getProjectById("1");
		
		
		assertEquals("ABC",project.getEmployeeId());
		assertEquals("01/20/2019",project.getEndDate());
		project.getNoOfTask();
		assertEquals("2",project.getPriority());
		project.getProjectId();
		
		assertEquals("Ebiz",project.getProjectName());
		assertEquals("01/20/2019",project.getStartDate());
		assertEquals("Active",project.getStatus());
		
	}
	
	
	@Test
	public void saveProject() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("ABC");
		project.setEndDate("01/20/2019");
		project.setNoOfTask(1L);
		project.setPriority("2");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("01/20/2019");
		project.setStatus("Active");
		boolean status=target.saveProject(project);
	}
	
	
	@Test
	public void updateProject() {
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("ABC");
		project.setEndDate("01/20/2019");
		project.setNoOfTask(1L);
		project.setPriority("2");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("01/20/2019");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		
		boolean status = target.updateProject(project,"1L");
		
	}
	
	@Test
	public void deleteprojects() {
		TaskVO taskVO=new TaskVO();
		ProjectVO project=new ProjectVO();
		project.setEmployeeId("ABC");
		project.setEndDate("01/20/2019");
		project.setNoOfTask(1L);
		project.setPriority("2");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("01/20/2019");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		boolean status = target.deleteprojects("1L");
	}
	
	@Test
	public void getusers() {
		List<UserVO> userList =new ArrayList<UserVO>();
		UserVO user=new UserVO();
		user.setEmployeeId("ABC");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userList.add(user);
		when(projectService.getAllUsers()).thenReturn(userList);
		List<UserVO> users =target.getusers();
		
	}
	
	@Test
	public void getUserById() {
		UserVO user=new UserVO();
		user.setEmployeeId("ABC");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		UserVO userVO =target.getUserById("1");
		
		assertEquals("ABC",user.getEmployeeId());
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("Active",user.getStatus());
	}
	
	@Test
	public void saveUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("ABC");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		boolean status=target.saveUser(user);
	}
	
	@Test
	public void updateUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("ABC");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		
		boolean status = target.updateUser(user,"1L");
		
	}
	
	@Test
	public void deleteUser() {
		UserVO user=new UserVO();
		user.setEmployeeId("ABC");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(projectService.getUser(Mockito.anyString())).thenReturn(user);
		boolean status = target.deleteUser("1L");
	}
	
}
