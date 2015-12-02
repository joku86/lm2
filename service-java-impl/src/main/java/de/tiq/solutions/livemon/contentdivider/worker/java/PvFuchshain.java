package de.tiq.solutions.livemon.contentdivider.worker.java;

import java.util.List;

import de.tiq.solutions.livemon.contentdivider.worker.ContentDivider;
import de.tiq.solutions.livemon.contentdivider.worker.ServiceLoaderInterface;
import de.tiq.solutions.livemon.model.IncomingData;
import de.tiq.solutions.livemon.model.LMData;

public class PvFuchshain extends ContentDivider implements ServiceLoaderInterface {

	@Override
	public void sayHello() {
		System.out.println("aus der Implementierung von PvFuchshain");

	}

	@Override
	public boolean matchPlant(String plantUUid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LMData> getOutgoing(IncomingData incoming) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateExisting(IncomingData incominigEntry, LMData outgoingEntry) {
		// TODO Auto-generated method stub

	}

	@Override
	protected LMData createNewLMData(IncomingData incoming) {
		// TODO Auto-generated method stub
		return null;
	}

}
