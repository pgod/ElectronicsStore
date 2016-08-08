package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import godziszewski.patryk.ElectronicsStore.exception.ProductNotFoundException;

@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request,
			ProductNotFoundException exception)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURI()+"?"+request.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

}
