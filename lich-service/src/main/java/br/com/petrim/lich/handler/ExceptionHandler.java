package br.com.petrim.lich.handler;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.vo.ErrorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> businessException(WebRequest request, BusinessException exception) {
        LOG.error(exception.getMessage(), exception);

        ErrorVo errorVo = new ErrorVo();
        errorVo.setMessage(exception.getMessage());

        return handleExceptionInternal(exception, errorVo, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
    }

}
