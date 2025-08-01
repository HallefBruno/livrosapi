package com.livro.api.livroapi.controller;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface GenericController {
	
	default URI gerarHeaderLocaltion(String uuid) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(uuid).toUri();
	}
	
}
