package mortoff.line.breaking.service;

import mortoff.line.breaking.model.LineBreakingContext;
import reactor.core.publisher.Mono;

public interface LineBreakerService {
	
	public void startLineBreak(String text, String id);

	public Mono<LineBreakingContext> getLineBreakById(String id);

}
