package de.tiq.solutions.livemon.websocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataAdapter {
	// private Queue<SendServerEndpoint> concurrentQueue = new
	// ConcurrentLinkedQueue<SendServerEndpoint>();
	// private Queue<ContentDivider> workers = new
	// ConcurrentLinkedQueue<ContentDivider>();
	// BasicDefinitionsLoader loader = new BasicDefinitionsLoader();
	private ExecutorService service = Executors.newSingleThreadExecutor();
	// private static final Logger logger = Logger.getLogger("DataAdapter");
	//
	// public void addContentWorker(ContentDivider worker) {
	// workers.add(worker);
	// }
	//
	// private synchronized List<LMData> getOutgoing(IncomingData incoming) {
	// List<LMData> empty = Collections.emptyList();
	// for (ContentDivider verteiler : workers) {
	// if (verteiler.matchPlant(incoming.getPlantUUID()))
	// return verteiler.getOutgoing(incoming);
	// }
	// return empty;
	// }
	//
	private static DataAdapter instance;

	private DataAdapter() {
	}

	public static DataAdapter getInstance() {
		if (DataAdapter.instance == null) {
			DataAdapter.instance = new DataAdapter();
		}
		return DataAdapter.instance;
	}

	// public void addListener(SendServerEndpoint monitoring) throws IOException
	// {
	// concurrentQueue.add(monitoring);
	// }
	//
	// public void removeListener(SendServerEndpoint monitoring) throws
	// IOException {
	// concurrentQueue.remove(monitoring);
	// }
	//
	public void update(final String incomingData) {
		service.submit(new Runnable() {
			public void run() {
				// try {
				// IncomingData asJavaObject = (IncomingData)
				// JsonMapper.getAsJavaObject(incomingData, IncomingData.class);
				// if
				// (loader.getFilterDefinitions().contains(asJavaObject.getMeasureID()))
				// {
				// List<LMData> outgoing = getOutgoing(asJavaObject);
				//
				// for (SendServerEndpoint sender : concurrentQueue) {
				// try {
				// for (LMData lmData : outgoing) {
				//
				// sender.send(JsonMapper.getAsString(lmData));
				// }
				//
				// } catch (Exception e) {
				// logger.error("Aktualisieren von SendeEndpunkten loeste einen Fehler aus "
				// + e.getMessage());
				// throw new RuntimeException(e);
				// }
				// }
				// }
				// } catch (IOException e) {
				// logger.error("Auslesen der Filterdatei loeste einen Fehler aus"
				// +
				// e.getMessage());
				// }
			}

		});

	}

}
