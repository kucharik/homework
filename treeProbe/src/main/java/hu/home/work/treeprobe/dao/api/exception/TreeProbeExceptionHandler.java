package hu.home.work.treeprobe.dao.api.exception;

public class TreeProbeExceptionHandler {

}



//
//package hu.raiffeisen.dcd.util.exception.handler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.NoSuchMessageException;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import hu.raiffeisen.sales.campaignadmin.pojo.BaseResponse;
//import hu.raiffeisen.sales.campaignadmin.pojo.message.ResponseMessage;
//import hu.raiffeisen.sales.campaignadmin.service.CampaignAdminService;
//import hu.raiffeisen.sales.campaignadmin.validation.ValidationResponseFactory;
//
//@ControllerAdvice
//public class BaseGlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//	@Autowired
//	ValidationResponseFactory validationResponseFactory;
//	
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	private final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();
//
//	@ExceptionHandler(ApplicationException.class)
//	public ResponseEntity<BaseResponse<String, ResponseMessage>> applicationExceptionHandler(Exception ex, WebRequest request) {
//
//		LOGGER.error("applicationExceptionHandler", ex);
//		ApplicationException x = (ApplicationException)ex;
//		String message = x.getMessage();
//		if (null != x.getCode()) {
//			message = resolveMessageByCode(x.getCode(), message);
//		}
//		return new ResponseEntity<BaseResponse<String, ResponseMessage>>(
//				BaseResponse.wrap(null, true, ResponseMessage.createError(x.getCode(), message)),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
//
//	private String resolveMessageByCode(String code, String message) {
//		switch (code) {
//		case CampaignAdminService.ERROR_CODE_NO_TRANSFERRED_CAMPAIGN_DATA:
//			return applicationContext.getMessage(CampaignAdminService.ERROR_PREFIX + code, null, LocaleContextHolder.getLocale());
//		default:
//			return message;
//		}
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		if (ex instanceof BindException) {
//			return new ResponseEntity(validationResponseFactory.getResponse(((BindException) ex).getBindingResult()),
//					HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity(BaseResponse.wrap(null, true, ResponseMessage.createError(null, ex.getMessage())),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@ExceptionHandler(NoSuchFieldException.class)
//	public ResponseEntity<BaseResponse<String, ResponseMessage>> noSuchFieldExceptionHandler(Exception ex, WebRequest request) {
//		LOGGER.error("noSuchFieldExceptionHandler", ex);
//		return new ResponseEntity<BaseResponse<String, ResponseMessage>>(
//				BaseResponse.wrap(null, true, ResponseMessage.createError(null, ex.getMessage())),
//				HttpStatus.BAD_REQUEST);
//
//	}
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<BaseResponse<String, ResponseMessage>> globalExceptionHandler(Exception ex,
//			WebRequest request) {
//		LOGGER.error("globalExceptionHandler", ex);
//		return new ResponseEntity<BaseResponse<String, ResponseMessage>>(
//				BaseResponse.wrap(null, true, ResponseMessage.createError(null, ex.getMessage())),
//				HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
//
//	@Override
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//
//		return new ResponseEntity(validationResponseFactory.getResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		// TODO pl jackson converter error itt kapható el...
//		return new ResponseEntity(ResponseMessage.createError(null, ex.getMessage()), status);
////		return super.handleHttpMessageNotReadable(ex, headers, status, request);
//	}
//
//	@ExceptionHandler(AccessDeniedException.class)
//	public ResponseEntity<BaseResponse<String, ResponseMessage>> accessDeniedExceptionHandler(Exception ex, WebRequest request) {
//
//		LOGGER.info("accessDeniedExceptionHandler", ex);
//		String code = "accessdenied";
//		String message = "Access denied.";
//		try {
//			message = applicationContext.getMessage(CampaignAdminService.ERROR_PREFIX + code, null, LocaleContextHolder.getLocale());
//		}
//		catch (NoSuchMessageException e)
//		{
//			LOGGER.warn(e.getMessage());
//			LOGGER.trace("handled exception",e);
//		}
//		
//		return new ResponseEntity<BaseResponse<String, ResponseMessage>>(
//				BaseResponse.wrap(null, true, ResponseMessage.createError(CampaignAdminService.ERROR_PREFIX + code, message)),
//				HttpStatus.FORBIDDEN);
//	}
//
//	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
//	public ResponseEntity<BaseResponse<String, ResponseMessage>> authenticationCredentialsNotFoundExceptionHandler(Exception ex, WebRequest request) {
//
//		LOGGER.info("authenticationCredentialsNotFoundException", ex);
//		String code = "notloggedin";
//		String message = "You must log in.";
//		try {
//			message = applicationContext.getMessage(CampaignAdminService.ERROR_PREFIX + code, null, LocaleContextHolder.getLocale());
//		}
//		catch (NoSuchMessageException e)
//		{
//			LOGGER.warn(e.getMessage());
//			LOGGER.trace("handled exception",e);
//		}
//		
//		return new ResponseEntity<BaseResponse<String, ResponseMessage>>(
//				BaseResponse.wrap(null, true, ResponseMessage.createError(CampaignAdminService.ERROR_PREFIX + code, message)),
//				HttpStatus.UNAUTHORIZED);
//	}
//	
//}