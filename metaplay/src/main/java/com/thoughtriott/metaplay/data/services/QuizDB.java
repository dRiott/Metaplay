//package com.thoughtriott.metaplay.data.services;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuizDB implements Quiz {
//	private List<Question> questions;
//	private String name = "State Capitals";
//
//	public QuizDB() {
//		//String URL = "jdbc:derby://localhost:1527/quiz";
//
//		String URL = "jdbc:sqlite:" + System.getProperty("user.home") + 
//				"/SD/Databases/quiz.db";
//
//		try (Connection conn = DriverManager.getConnection(URL)) {
//			loadQuestions(conn);
//		} catch (SQLException e) {
//			System.err.println(e);
//			throw new RuntimeException(e);
//		}
//	}
//	
//	@Override
//	public String getQuizName() {
//		return name;
//	}
//	
//	private void loadQuestions(Connection conn) throws SQLException {
//
//		String questionSQL = "SELECT qu.id, text "
//				+ "FROM quiz q, question qu, quiz_question qq "
//				+ "WHERE q.id = qq.quiz_id " + "AND qu.id = qq.question_id "
//				+ "AND q.name = ?";
//
//		PreparedStatement pstmt = conn.prepareStatement(questionSQL);
//		pstmt.setString(1, name);
//		ResultSet rs = pstmt.executeQuery();
//
//		questions = new ArrayList<>();
//		while (rs.next()) {
//			int questionId = rs.getInt(1);
//			String questionText = rs.getString(2);
//
//			List<Answer> answers = new ArrayList<>();
//			String answerSQL = "SELECT text, iscorrect " + "FROM answer "
//					+ "WHERE question_id = ?";
//			PreparedStatement pstmt2 = conn.prepareStatement(answerSQL);
//			pstmt2.setInt(1, questionId);
//			ResultSet rs2 = pstmt2.executeQuery();
//			while (rs2.next()) {
//				String answerText = rs2.getString(1);
//				boolean isCorrect = rs2.getBoolean(2);
//				answers.add(new Answer(answerText, isCorrect));
//			}
//			rs2.close();
//			pstmt2.close();
//
//			Question question = new Question(questionId, questionText, answers);
//			questions.add(question);
//		}
//		rs.close();
//		pstmt.close();
//	}
//
//	public int getNumberOfQuestions() {
//		return questions.size();
//	}
//
//	public List<Question> getQuestions() {
//		return questions;
//	}
//
//	void setQuestions(List<Question> questions) {
//		this.questions = questions;
//	}
//
//	public String getResults() {
//		StringBuilder builder = new StringBuilder(1024);
//		for (Question question : questions) {
//			builder.append("Question: " + question.getValue() + "\n");
//			List<Answer> answers = question.getAnswers();
//			for (Answer answer : answers) {
//				if (answer.isCorrect()) {
//					builder.append("  *");
//				} else {
//					builder.append("   ");
//				}
//				builder.append("Answer: " + answer.getValue() + "\n");
//			}
//			builder.append("User Answer: " + question.getGivenAnswer());
//			if (question.getCorrectAnswer().getValue()
//					.equals(question.getGivenAnswer())) {
//				builder.append(" --> CORRECT" + "\n");
//			} else {
//				builder.append(" --> INCORRECT" + "\n");
//			}
//			builder.append("\n");
//		}
//		return builder.toString();
//	}
//}
