package com.ciklum.hybris.internship.eesection.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CrudRepository {
    void create(HttpServletRequest req, HttpServletResponse resp);

    void read(HttpServletRequest req, HttpServletResponse resp);

    void delete(HttpServletRequest req, HttpServletResponse resp);

    void update(HttpServletRequest req, HttpServletResponse resp);

}
