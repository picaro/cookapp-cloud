package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.CircleDao;
import com.op.cookcloud.model.base.Circle;
import com.op.cookcloud.model.base.ShopList;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service("circleService")
@Transactional
@RequestMapping("/circle")
@Log4j
public class CircleController {

    @Autowired
    private CircleDao circleDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @RequestMapping("/allCircles")
    public ModelAndView readAll() {
        List<Circle> circleList = circleDao.findAll();
        return new ModelAndView("circles","circleList", circleList);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Circle read(@PathParam("id") Integer id) {
        Circle circle = circleDao.findById(id);
        log.debug("Circle found, id: " + id);
        return circle;
    }

    @PUT
    public void update(@ModelAttribute Circle circle) {
        circleDao.saveOrUpdate(circle);
        log.debug("Circle updated: " + circle);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Circle circle) {
        circleDao.saveOrUpdate(circle);
        log.debug("Circle created: " + circle);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        Circle circle = circleDao.findById(id);
        circleDao.delete(circle);
        log.debug("Circle deleted: " + circle);
    }


}