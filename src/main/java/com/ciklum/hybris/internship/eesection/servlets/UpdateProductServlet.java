package com.ciklum.hybris.internship.eesection.servlets;

import com.ciklum.hybris.internship.eesection.repository.CrudRepository;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductServlet implements HttpRequestHandler {
    private final CrudRepository repository;

    public UpdateProductServlet(CrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        repository.update(req, resp);
    }
}
