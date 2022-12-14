package mortoff.line.breaking.dao;

import java.util.List;

import mortoff.line.breaking.model.LineBreakingContext;
import mortoff.line.breaking.model.Status;
import reactor.core.publisher.Mono;

public interface InMemoryDao {
	
	/**
	 * @return 
	 */
	public Mono<LineBreakingContext> getLineBreakContextById(String id);

	/**
	 * Saves the given text into memory
	 * @return 
	 * processing id in UUID format
	 */
	void initContext(String text, String id);
	
	void updateContext(String id, Status status);

	void updateContext(String id, Status status, List<String> brokenLines);

}
