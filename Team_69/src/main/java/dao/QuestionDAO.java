package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;
import bean.Question;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import bean.Answer;
import bean.HibernateUtil;
import bean.Quiz;

public class QuestionDAO {

	public void addQuestion(Question question) {

		Transaction transaction = null;
		try  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(question);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Question> getQuestionsByQuizId(int quizId){

		Transaction transaction = null;
		List<Question> quesList = new ArrayList<Question>();

		try  {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Question> query = builder.createQuery(Question.class);
			Root<Question> root = query.from(Question.class);
			Join<Question,Quiz> join = root.join("quiz");
			query.select(root).where(builder.equal(join.get("quizId"),quizId));
			Query<Question> q = session.createQuery(query);
			quesList = q.getResultList();
			for(Question qu: quesList)
				System.out.println(qu.toString());
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return quesList;
		}
		return quesList;
	}
	
	/**
	 * This method will delete questions from the quiz.
	 */
	public void deleteQuestionByQuestionId(String quesId){
		Transaction transaction = null;
		Question quesList = null;
		try  {
			int qId = Integer.parseInt(quesId);
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			quesList = (Question) session.get(Question.class, qId);
			session.delete(quesList);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return ;
		}
		return ;
	}
		
	/**
	 * Joins the question and answer table in Database.
	 */
	public List<Answer> getQuestionsAndAnswers(int quizId) {
		Transaction transaction = null;
	       List<Answer> quesList = new ArrayList<Answer>();
	       try  {
	           Session session = HibernateUtil.getSessionFactory().openSession();
	           transaction = session.beginTransaction();
	           CriteriaBuilder builder = session.getCriteriaBuilder();
	           CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
	           Root<Answer> root = query.from(Answer.class);
	           Join<Answer, Question> join = root.join("question");
	           query.select(root).where(root.get("quiz").in(quizId));
	           Query<Answer> q=session.createQuery(query);
	           quesList= q.getResultList();
	           transaction.commit();
	       } catch (HibernateException e) {
	           e.printStackTrace();
	           if (transaction != null) {
	               transaction.rollback();
	           }
	       }
	       return quesList;
	}
	
}
