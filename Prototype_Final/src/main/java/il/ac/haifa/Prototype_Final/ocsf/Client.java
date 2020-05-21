package il.ac.haifa.Prototype_Final.ocsf;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.hibernate.Session;

import il.ac.haifa.Prototype_Final.App;
import il.ac.haifa.Prototype_Final.Question;
import il.ac.haifa.Prototype_Final.SecondaryController;
import il.ac.haifa.Prototype_Final.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private static List<Question> questions;
	private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	
	public Client(String host, int port) {
		super(host, port);
	}
	
	
	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg.getClass()==Question.class) {
			System.out.println("Question #" + ((Question)msg).getId() +" was updated by server");
			//questions.set(((Question) msg).getId()-1, (Question) msg);
			questions.set(((Question) msg).getId()-1, (Question) msg);
			return;
		}
		if (msg.getClass()==String.class) {
			return;
		}
		questions = (List<Question>) msg;
	}
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Required arguments: <host> <port>");
		} else {
			String host = args[0];
			int port = Integer.parseInt(args[1]);

			Client client = new Client(host, port);
			client.openConnection();
			App.setClient_App(client);
			App.main(args);
		}
	}
	public static List<Question> getQuestions() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}
}
