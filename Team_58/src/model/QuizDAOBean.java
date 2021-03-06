package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Class QuizDAOBean is a class that comes after Professor Home Page 
 * and Course Dashboard page 
 * 
 * @author narenkumarkonchada & carnic
 * @version 1.2
 * @date 02/22/2019
 **/

public class QuizDAOBean implements QuizDAO{
	
	private static Properties dbProperties = new Properties();
	
	public QuizDAOBean() {
		
		try {
			dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to get the list of all Quizzes 
	 *@param request  Request made to server
	 *@param response  Responses from server
	 *
	 * @throws IOException, ServletException
	 */
	
	@Override
	public List<QuizVO> getQuizzesForCourse(int courseId) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet resultData = null;
		
		connection = ConnectionFactory.getConnection();
		List<QuizVO> list = new ArrayList<>();
				
		try{
			query = connection.prepareStatement(dbProperties.getProperty("getQuizzesForACourse"));
			query.setInt(1, courseId);
	
			resultData = query.executeQuery();
			
			while(resultData.next()) {
				int quizId = resultData.getInt("quizId");
				int assignedTime = resultData.getInt("assignedTime");
				boolean isGraded = resultData.getBoolean("isGraded");
				String quizInstruction = resultData.getString("quizInstruction"); 
				Date quizScheduledDate = resultData.getDate("quizScheduledDate");
				boolean isShuffled = resultData.getBoolean("isShuffled");
				String quizTitle = resultData.getString("quizTitle");
				QuizVO quiz = new QuizVO(quizId, isGraded, assignedTime, quizInstruction, quizScheduledDate, isShuffled, quizTitle);
				list.add(quiz);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			resultData = null;
			query = null;
			connection = null;
		}
		return list;
	}

	/**
	 * This method is to create a quiz
	 * Insert quiz details into quiz table 
	 *@param CreateQuizVO object to insert values
	 *
	 * @throws SQLException, ClassNotFoundException
	 */
	
	public void insertingQuizDetails(CreateQuizVO createQuizVO) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet resultData = null;
		
		connection = ConnectionFactory.getConnection();
		
		try{       
			query = connection.prepareStatement(dbProperties.getProperty("postCreateQuiz"));
			query.setInt(1,createQuizVO.getCourseId());
			query.setBoolean(2, false);
			query.setInt(3, createQuizVO.getAssignedTime());
			query.setString(4, createQuizVO.getQuizInstructions());
			query.setString(5, createQuizVO.getQuizScheduledDate());
			query.setBoolean(6, createQuizVO.getIsShuffled());
			query.setString(7, createQuizVO.getQuizTitle());
			query.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			resultData = null;
			query = null;
			connection = null;
		}
	}
	
	/**
	 * This method is to retrieve quiz id from db
	 *@param CreateQuizVO object to retrieve value 
	 *
	 * @throws ClassNotFoundException, ServletException
	 */
	
	public int gettingQuizId (CreateQuizVO createQuizVO) throws  SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement query = null;
		connection = ConnectionFactory.getConnection();
		int quizId = 0;
		ResultSet rs = null;
		
		try {
			query = connection.prepareStatement(dbProperties.getProperty("getQuizId"));
			query.setInt(1, createQuizVO.getCourseId());
			query.setBoolean(2, createQuizVO.getIsGraded());
			query.setInt(3, createQuizVO.getAssignedTime());
			query.setString(4, createQuizVO.getQuizInstructions());
			query.setString(5, createQuizVO.getQuizScheduledDate());
			query.setBoolean(6, createQuizVO.getIsShuffled());
			query.setString(7, createQuizVO.getQuizTitle());
			rs = query.executeQuery();
			
			while (rs.next()) {
				quizId = rs.getInt("quizId");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			rs = null;
			query = null;
			connection = null;
		}
		return quizId;
	}

	@Override
	public List<QuizVO> getQuizzesForStudent(UserVO student) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet resultData = null;
		
		connection = ConnectionFactory.getConnection();
		
		query = connection.prepareStatement(dbProperties.getProperty("getQuizzesForStudent"));
		query.setInt(1, student.getUserId());

		resultData = query.executeQuery();
		
		List<QuizVO> list = new ArrayList<>();
		
		while(resultData.next()) {
			int quizId = resultData.getInt("quizId");
			int assignedTime = resultData.getInt("assignedTime");
			boolean isGraded = resultData.getBoolean("isGraded");
			String quizInstruction = resultData.getString("quizInstruction"); 
			Date quizScheduledDate = resultData.getDate("quizScheduledDate");
			boolean isShuffled = resultData.getBoolean("isShuffled");
			String quizTitle = resultData.getString("quizTitle");
			QuizVO quiz = new QuizVO(quizId, isGraded, assignedTime, quizInstruction, quizScheduledDate, isShuffled, quizTitle);
			list.add(quiz);
		}
		
		return list;
	}

}