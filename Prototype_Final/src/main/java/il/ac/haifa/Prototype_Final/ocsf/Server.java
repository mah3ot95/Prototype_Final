package il.ac.haifa.Prototype_Final.ocsf;


import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.ac.haifa.Prototype_Final.Question;
import il.ac.haifa.Prototype_Final.ocsf.server.AbstractServer;
import il.ac.haifa.Prototype_Final.ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	private static List<Question> questions;
	private static Session session;
	public Server(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("Received Message: " + msg.toString());
		if (msg.getClass()==Question.class) {
			System.out.println("client " +client.getId()+" updated question #"+ ((Question)msg).getId());
			try {
				session.beginTransaction();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				session.merge((Question) msg);
				session.flush();
				session.getTransaction().commit(); // Save everything.
				
				System.out.println("question #" +((Question)msg).getId()+" was successfully updated in database");
				
				System.out.println("notify all clients about the update");
				sendToAllClients(msg);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error! question #" +((Question)msg).getId()+" was NOT successfully updated in database");
				e.printStackTrace();
			}
		}
		if (msg.getClass()==String.class) {
			try {
		    	System.out.println("client " + client.getId() +" asked server for questions");
				client.sendToClient(getAllQuestions());
				System.out.println("All questions were sent to "+client.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("error! questions were not sent!");
				e.printStackTrace();
			}
		}
	}
	
	private static List<Question> getAllQuestions() throws Exception {
		try {
			session.beginTransaction();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Question> query = builder.createQuery(Question.class);
		query.from(Question.class);
		questions = session.createQuery(query).getResultList();
		return questions;
		
	}
	
	
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		
		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}
	
	

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		/*
		System.out.println("Client connected: " + client.getId());
		System.out.println("send questions to client");
		try {
			client.sendToClient(getAllQuestions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			Server server = new Server(Integer.parseInt(args[0]));
			server.listen();
			createSession();
		}
	}

	private static void createSession() {
		// TODO Auto-generated method stub
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			generateDatabese();
			//Server.setSession(session);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
    public static void generateDatabese() throws Exception {
    	Question[] questions = new Question[6];
    	
    	questions[0] = new Question("question_1", "ans_a_1", "ans_b_1", "ans_c_1", "ans_d_1", "correct_ans_1");
    	questions[1] = new Question("question_2", "ans_a_2", "ans_b_2", "ans_c_2", "ans_d_2", "correct_ans_2");
    	questions[2] = new Question("question_3", "ans_a_3", "ans_b_3", "ans_c_3", "ans_d_3", "correct_ans_3");
    	questions[3] = new Question("question_4", "ans_a_4", "ans_b_4", "ans_c_4", "ans_d_4", "correct_ans_4");
    	questions[4] = new Question("question_5", "ans_a_5", "ans_b_5", "ans_c_5", "ans_d_5", "correct_ans_5");
    	questions[5] = new Question("question_6", "ans_a_6", "ans_b_6", "ans_c_6", "ans_d_6", "correct_ans_6");
    	
    	for(int i=0;i<6;i++) {
			 session.save(questions[i]);
		 }
    	session.flush();
    	session.getTransaction().commit(); // Save everything.
    }
	private static SessionFactory getSessionFactory() throws HibernateException {
	 	 Configuration configuration = new Configuration();
	 	 // Add ALL of your entities here. You can also try adding a whole
	 	 configuration.addAnnotatedClass(Question.class);
	 	 ServiceRegistry serviceRegistry = 
	 			 new StandardServiceRegistryBuilder()
	 			 .applySettings(configuration.getProperties())
	 			 .build();
	 	 return configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		Server.session = session;
	}
}
