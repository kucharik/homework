package mortoff.line.breaking.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mortoff.line.breaking.model.LineBreakingContext;
import mortoff.line.breaking.model.Status;
import reactor.core.publisher.Mono;

public class InMemoryDaoImpl implements InMemoryDao{
	
	private Map<String, LineBreakingContext> elements;
	
	public InMemoryDaoImpl() {
		this.elements = new ConcurrentHashMap<String, LineBreakingContext>();
	}

	@Override
	public void initContext(String text, String id) {
		
		LineBreakingContext element = new LineBreakingContext();
		element.setRawData(text);
		element.setStatus(Status.RAW);
		elements.put(id, element);
	}
	
	@Override
	public void updateContext(String id, Status status) {
		updateContext(id, status, null);
	}
	
	@Override
	public void updateContext(String id, Status status, List<String> brokenLines) {
		LineBreakingContext element = elements.get(id);
		element.setStatus(status);
		element.setBrokenText(brokenLines);
	}

	

	@Override
	public Mono<LineBreakingContext> getLineBreakContextById(String id) {
		return Mono.justOrEmpty(elements.get(id));
	}


}
