package mortoff.line.breaking.controller;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mortoff.line.breaking.service.LineBreakerService;
import reactor.core.publisher.Mono;

@RestController
public class LineBreakingController {
	
	private static final Logger logger = LoggerFactory.getLogger(LineBreakingController.class);
	
	static {
		logger.trace("INIT CONTROLLER Class!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Autowired
	private LineBreakerService lineBreakerService;
	
	
	@PostMapping("/api/LineBreak")
	public Mono<Object> startLineBreak(@RequestBody com.fasterxml.jackson.databind.JsonNode payload) {
		String processId = UUID.randomUUID().toString();
		lineBreakerService.startLineBreak((payload != null && payload.get("text") != null) ? payload.get("text").asText() : "", processId);
		return Mono.just(processId);
	}
	
	@GetMapping("/api/LineBreak/{id}")
	@ResponseBody
	public Mono<ResponseEntity<Object>> getLineBreakById(@PathVariable String id) {
		return lineBreakerService.getLineBreakById(id)
				.map(process -> new ResponseEntity<Object>(process.getBrokenText(), process.getStatus().isReady() ? HttpStatus.OK : HttpStatus.ACCEPTED))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostConstruct
	private void contextTest() {
		logger.trace("  ******PostConstruct***********  ");
		
	}
}
