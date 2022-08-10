package com.alvion.alviontask.services;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import com.alvion.alviontask.config.ExcelConfig;
import com.alvion.alviontask.jparepos.CourseRepo;
import com.alvion.alviontask.jparepos.CourseStudyingRepo;
import com.alvion.alviontask.jparepos.ProfessorRepo;
import com.alvion.alviontask.jparepos.StudentRepo;
import com.alvion.alviontask.model.Course;
import com.alvion.alviontask.model.CourseStudying;
import com.alvion.alviontask.model.Professor;
import com.alvion.alviontask.model.ReportObject;
import com.alvion.alviontask.model.Student;

@Component
public class Service {

	private CourseRepo courseRepo;
	private ProfessorRepo professorRepo;
	private StudentRepo studentRepo;
	private CourseStudyingRepo courseStudyingRepo;
	
	@Autowired
	public Service(CourseRepo courseRepo, ProfessorRepo professorRepo, 
			StudentRepo studentRepo, CourseStudyingRepo courseStudyingRepo) {
		this.courseRepo = courseRepo;
		this.professorRepo = professorRepo;
		this.studentRepo = studentRepo;
		this.courseStudyingRepo = courseStudyingRepo;
	}

	/**
	 * Ищем студента и курс по номерам. Добавляем на курс студента и студенту - проходимый курс.
	 * @param courseNumber
	 * @param studentBookNumber
	 */
	@Modifying
	@Transactional
	public void addStudentToCourse(Long courseNumber, Long studentBookNumber) {
		Student studentToAdd = studentRepo.findById(studentBookNumber).get();
		Course intendedCourse = courseRepo.findById(courseNumber).get();
		CourseStudying courseStudying = new CourseStudying(intendedCourse, 0, 0, 0.0f, studentToAdd);
		studentToAdd.getCoursesProgress().add(courseStudying);
		intendedCourse.addStudentToCourse(studentToAdd);
		courseStudyingRepo.save(courseStudying);
		courseRepo.save(intendedCourse);
	}
	
	/**
	 * Ищем студента и курс по номерам. Удаляем из курса студента 
	 * и добавляем студенту пройденный курс с рандомной оценкой. 
	 * Обновляем среднюю оценку студента.
	 * @param courseNumber
	 * @param studentBookNumber
	 */
	public void removeStudentFromCourse(Long courseNumber, Long studentBookNumber) {
		Student studentToRemove = studentRepo.findById(studentBookNumber).get();
		Course intendedCourse = courseRepo.findById(courseNumber).get();
		Random r = new Random();
		for (CourseStudying course:courseStudyingRepo.findAll()) {
			if (course.getStudent().getNumberOfRecordBook() == studentBookNumber) {
				course.setFinalGrade(1 + (r.nextInt(5)));
			}
		}
		studentToRemove.getCourses().remove(intendedCourse);
		intendedCourse.getStudentsOnCourse().remove(studentToRemove);
		updateAverageGrade(studentToRemove);
		courseRepo.save(intendedCourse);
	}
	
	/**
	 * Ищем профессора и курс. Устанавливаем в поле курса найденного профессора, профессору добавляем курс.
	 * @param professorsName
	 * @param courseNumber
	 */
	public void assignProfessorOnCourse(Long id, Long courseNumber) {
		Professor professorToAssign = professorRepo.findById(id).get();
		Course intendedCourse = courseRepo.findById(courseNumber).get();
		intendedCourse.setProfessor(professorToAssign);
		courseRepo.save(intendedCourse);
	}
	
	/**
	 * Вычисляем среднюю оценку студента из финальных оценок законченных курсов и текущих средних оценок незаконченных курсов.
	 * @param student
	 */
	public void updateAverageGrade(Student student) {
		student.calculateAverageGrade();
		studentRepo.save(student);
	}
	
	// Для заполнения данных
	public void generateAndGiveGrades() {
		List<Course> courses = courseRepo.findAll();
		Random r = new Random();
		for (Course course:courses) {
			for (Student student:course.getStudentsOnCourse()) {
				for (CourseStudying studying:student.getCoursesProgress()) {
					studying.addGradeAndUpdate(1 + (r.nextInt(5)));
				}
				updateAverageGrade(student);
			}
		}
	}
	
	/**
	 * Создание Excel файла
	 * @param objects
	 */
	public void generateExcelFile(List<ReportObject> objects) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Отчёт пед.состава");
		
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = sheet.createRow(0);
		
		for (int i = 0; i < ExcelConfig.columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(ExcelConfig.columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		int rowNum = 1;
		
		for (ReportObject obj:objects) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(obj.getProfessorsName());
			row.createCell(1).setCellValue(obj.getNumberOfStudents());
			row.createCell(2).setCellValue(obj.getAverageGradeOfStudents());
		}
		
		for (int i = 0; i < ExcelConfig.columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream("report.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ReportObject> gatherAndCreateDataForReport() {
		List<ReportObject> objects = new ArrayList<>();
		for (Professor professor:professorRepo.findAll()) {
			
			ReportObject obj = new ReportObject();
			obj.setProfessorsName(professor.getName());
			
			int numberOfStudents = 0;
			float summaryForAllCourses = 0.0f;
			
			for (Course course:professor.getCourses()) {
				
				// Суммируем количество студентов по курсам.
				numberOfStudents = numberOfStudents + course.getStudentsOnCourse().size();
				
				float summaryForCourse = 0.0f;
				
				for (Student student:course.getStudentsOnCourse()) {
					
					// Суммируем оценки по курсам, чтобы определить среднюю оценку студентов на одном курсе.
					summaryForCourse = summaryForCourse + student.calculateAverageGrade();
				}
				
				// Средняя по курсу.
				float averageForCourse = summaryForCourse / course.getStudentsOnCourse().size();
				
				// Суммируем средние оценки по курсам.
				summaryForAllCourses = summaryForAllCourses + averageForCourse;
			}
			
			// Получаем среднюю оценку всех студентов.
			float averageForAllCourses = summaryForAllCourses / professor.getCourses().size();
			
			obj.setNumberOfStudents(numberOfStudents);
			obj.setAverageGradeOfStudents(averageForAllCourses);
			objects.add(obj);
		}
		return objects;
	}
}
