package br.com.bruno.skipthedishes.api.rest;

import static br.com.bruno.skipthedishes.api.rest.BaseV1RestController.V1_URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(V1_URI)
public abstract class BaseV1RestController {
	
	public static final String V1_URI = "/api/v1";

}
