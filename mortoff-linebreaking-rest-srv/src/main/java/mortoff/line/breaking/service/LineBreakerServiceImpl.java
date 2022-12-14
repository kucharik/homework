package mortoff.line.breaking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mortoff.line.breaking.dao.InMemoryDao;
import mortoff.line.breaking.model.LineBreakingContext;
import mortoff.line.breaking.model.Status;
import reactor.core.publisher.Mono;

@Service
public class LineBreakerServiceImpl implements LineBreakerService {
	
	private static final Logger logger = LoggerFactory.getLogger(LineBreakerServiceImpl.class);
	
	@Autowired
	private InMemoryDao dao;
	
	@Value( "${line.max.length:5}" )
	private int maxLineLength;
	
	@Value( "${process.delay.millies:0}" )
	private int delayInMillies;
	

	@Async
	@Override
	public void startLineBreak(String text, String id) {
		dao.initContext(text, id);
		try {
			logger.debug("before sleep...");
			Thread.sleep(delayInMillies);
			dao.updateContext(id, Status.PROCESSING);
			Thread.sleep(delayInMillies);
			logger.debug("after sleep...");
		} catch (InterruptedException e) {
			logger.error("Error on sleeping...");
		}
		dao.updateContext(id, Status.READY, getBrokenLines(text));
	}
	
	
	@Override
	public Mono<LineBreakingContext> getLineBreakById(String id) {
	    Mono<LineBreakingContext> element = dao.getLineBreakContextById(id)
			.filter(a -> a instanceof LineBreakingContext)
	        .flatMap(c -> Mono.justOrEmpty(c));
	    
		return element;
	}
	
	protected List<String> getBrokenLines(String text) {

			List<String> elements = new ArrayList<String>();

			List<String> spaceEndedWords = Stream.of(text.replaceAll("\\s+", " ").split(" "))
						.map(elem -> new String(elem + " "))
						.collect(Collectors.toList());
			
			boolean hasTrailingSpace = text.endsWith(" ");
			
			StringBuffer line = new StringBuffer();
			
			for (String spaceEndedWord : spaceEndedWords) {
				if ( (line.length() + spaceEndedWord.length() - 1) <= maxLineLength) {
					if ( (line.length() + spaceEndedWord.length()) > maxLineLength) {
						line.append(spaceEndedWord.trim());
					} else {
						line.append(spaceEndedWord);
					}
				} else {
					elements.add(line.toString());
					line = new StringBuffer();
					if (spaceEndedWord.length() >= maxLineLength) {
						line.append(spaceEndedWord.trim());
					} else {
						line.append(spaceEndedWord);
					}
				}
			}
			if (line.length() > 0) {
				if (!hasTrailingSpace) {
					elements.add(line.toString().trim());
				} else {
					elements.add(line.toString());
				}	
			}
			
			return elements;
	}
}