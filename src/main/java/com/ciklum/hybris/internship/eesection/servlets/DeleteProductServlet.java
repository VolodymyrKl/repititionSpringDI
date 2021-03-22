package com.ciklum.hybris.internship.eesection.servlets;

import com.ciklum.hybris.internship.eesection.repository.CrudRepository;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductServlet implements HttpRequestHandler {
    private final CrudRepository repository;

    public DeleteProductServlet(CrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        repository.delete(req, resp);
    }
}
